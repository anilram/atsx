-- PostgreSQL Database Schema for Resume AI Evaluation System
-- Requires PostgreSQL 15+ with pgvector extension

-- Enable pgvector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- ============================================
-- BASE TABLES
-- ============================================

-- Users table for authentication and authorization
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'RECRUITER', -- ADMIN, RECRUITER, CANDIDATE
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP WITH TIME ZONE
);

-- Resumes table
CREATE TABLE resumes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500),
    file_size BIGINT,
    mime_type VARCHAR(100),
    raw_text TEXT,
    candidate_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    total_experience_years NUMERIC(4,2),
    current_role VARCHAR(255),
    summary TEXT,
    parsing_status VARCHAR(50) DEFAULT 'PENDING', -- PENDING, PROCESSING, COMPLETED, FAILED
    parsed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Descriptions table
CREATE TABLE job_descriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    file_size BIGINT,
    mime_type VARCHAR(100),
    raw_text TEXT,
    job_title VARCHAR(255),
    required_experience_years NUMERIC(4,2),
    domain VARCHAR(255),
    parsing_status VARCHAR(50) DEFAULT 'PENDING',
    parsed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- RESUME DETAILS TABLES
-- ============================================

-- Resume Skills
CREATE TABLE resume_skills (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100), -- Backend, Frontend, Cloud, DevOps, Database, Testing, AI, Other
    years_of_experience NUMERIC(4,2),
    level VARCHAR(50), -- Beginner, Intermediate, Advanced, Expert
    embedding VECTOR(1536), -- Skill embedding for semantic matching
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Degrees
CREATE TABLE resume_degrees (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    degree VARCHAR(255) NOT NULL,
    university VARCHAR(255),
    graduation_year VARCHAR(10),
    field_of_study VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Certifications
CREATE TABLE resume_certifications (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    issuer VARCHAR(255),
    year VARCHAR(10),
    credential_id VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Achievements
CREATE TABLE resume_achievements (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    impact TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Projects
CREATE TABLE resume_projects (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    technologies TEXT[], -- Array of technology names
    start_date VARCHAR(20),
    end_date VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- JOB DESCRIPTION DETAILS TABLES
-- ============================================

-- Job Required Skills
CREATE TABLE job_skills (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    weight INTEGER DEFAULT 1, -- Importance weight (1-5)
    category VARCHAR(100),
    embedding VECTOR(1536), -- Skill embedding for semantic matching
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Education Requirements
CREATE TABLE job_education_requirements (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    requirement VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Certification Requirements
CREATE TABLE job_certification_requirements (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    requirement VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Soft Skills
CREATE TABLE job_soft_skills (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    skill VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Responsibilities
CREATE TABLE job_responsibilities (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    responsibility TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- EVALUATION TABLES
-- ============================================

-- Evaluation Results
CREATE TABLE evaluation_results (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    overall_match_percentage NUMERIC(5,2) NOT NULL,
    recommendation VARCHAR(50) NOT NULL, -- Strong Match, Moderate Match, Weak Match, Reject
    high_level_summary TEXT,
    
    -- Individual Scores
    technical_skill_score NUMERIC(5,2),
    experience_score NUMERIC(5,2),
    domain_score NUMERIC(5,2),
    education_score NUMERIC(5,2),
    certification_score NUMERIC(5,2),
    achievement_score NUMERIC(5,2),
    soft_skill_score NUMERIC(5,2),
    
    -- Strengths and Improvements
    strengths TEXT[], -- Array of strength descriptions
    improvements TEXT[], -- Array of improvement suggestions
    
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    -- Ensure unique evaluation per resume-job pair
    UNIQUE(resume_id, job_description_id)
);

-- Skill Match Results
CREATE TABLE skill_match_results (
    id BIGSERIAL PRIMARY KEY,
    evaluation_result_id BIGINT NOT NULL REFERENCES evaluation_results(id) ON DELETE CASCADE,
    resume_skill_id BIGINT REFERENCES resume_skills(id),
    job_skill_id BIGINT REFERENCES job_skills(id),
    resume_skill_name VARCHAR(255) NOT NULL,
    job_skill_name VARCHAR(255) NOT NULL,
    match_type VARCHAR(50) NOT NULL, -- EXACT, PARTIAL, SEMANTIC, MISSING
    similarity_score NUMERIC(5,4), -- For semantic matches (0.0 to 1.0)
    explanation TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- EMBEDDINGS TABLES (for pgvector)
-- ============================================

-- Resume Embeddings (overall resume vector)
CREATE TABLE resume_embeddings (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    embedding_type VARCHAR(50) NOT NULL, -- SKILL, EXPERIENCE, DOMAIN, OVERALL
    embedding VECTOR(1536) NOT NULL,
    metadata JSONB, -- Additional metadata about the embedding
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Description Embeddings
CREATE TABLE job_description_embeddings (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    embedding_type VARCHAR(50) NOT NULL, -- SKILL, DOMAIN, OVERALL
    embedding VECTOR(1536) NOT NULL,
    metadata JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- INDEXES FOR PERFORMANCE
-- ============================================

-- B-tree indexes for foreign keys and frequently queried columns
CREATE INDEX idx_resumes_user_id ON resumes(user_id);
CREATE INDEX idx_resumes_parsing_status ON resumes(parsing_status);
CREATE INDEX idx_job_descriptions_user_id ON job_descriptions(user_id);
CREATE INDEX idx_job_descriptions_parsing_status ON job_descriptions(parsing_status);
CREATE INDEX idx_resume_skills_resume_id ON resume_skills(resume_id);
CREATE INDEX idx_resume_skills_name ON resume_skills(name);
CREATE INDEX idx_job_skills_job_description_id ON job_skills(job_description_id);
CREATE INDEX idx_job_skills_name ON job_skills(name);
CREATE INDEX idx_evaluation_results_resume_id ON evaluation_results(resume_id);
CREATE INDEX idx_evaluation_results_job_description_id ON evaluation_results(job_description_id);
CREATE INDEX idx_evaluation_results_created_at ON evaluation_results(created_at DESC);
CREATE INDEX idx_skill_match_results_evaluation_id ON skill_match_results(evaluation_result_id);

-- GIN indexes for array columns
CREATE INDEX idx_resume_projects_technologies ON resume_projects USING GIN(technologies);
CREATE INDEX idx_evaluation_results_strengths ON evaluation_results USING GIN(strengths);
CREATE INDEX idx_evaluation_results_improvements ON evaluation_results USING GIN(improvements);

-- Vector indexes for similarity search (IVFFlat for better performance on large datasets)
CREATE INDEX idx_resume_skills_embedding ON resume_skills USING ivfflat(embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_job_skills_embedding ON job_skills USING ivfflat(embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_resume_embeddings_embedding ON resume_embeddings USING ivfflat(embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_job_description_embeddings_embedding ON job_description_embeddings USING ivfflat(embedding vector_cosine_ops) WITH (lists = 100);

-- Full-text search indexes
CREATE INDEX idx_resumes_raw_text ON resumes USING GIN(to_tsvector('english', raw_text));
CREATE INDEX idx_job_descriptions_raw_text ON job_descriptions USING GIN(to_tsvector('english', raw_text));

-- ============================================
-- TRIGGERS FOR UPDATED_AT
-- ============================================

-- Function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Apply trigger to tables with updated_at
CREATE TRIGGER update_resumes_updated_at BEFORE UPDATE ON resumes
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_job_descriptions_updated_at BEFORE UPDATE ON job_descriptions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_evaluation_results_updated_at BEFORE UPDATE ON evaluation_results
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ============================================
-- VIEWS FOR COMMON QUERIES
-- ============================================

-- View for resume summary with skill count
CREATE VIEW v_resume_summary AS
SELECT 
    r.id,
    r.candidate_name,
    r.email,
    r.total_experience_years,
    r.current_role,
    r.parsing_status,
    COUNT(DISTINCT rs.id) as skill_count,
    COUNT(DISTINCT rd.id) as degree_count,
    COUNT(DISTINCT rc.id) as certification_count,
    r.created_at
FROM resumes r
LEFT JOIN resume_skills rs ON r.id = rs.resume_id
LEFT JOIN resume_degrees rd ON r.id = rd.resume_id
LEFT JOIN resume_certifications rc ON r.id = rc.resume_id
GROUP BY r.id;

-- View for job description summary
CREATE VIEW v_job_description_summary AS
SELECT 
    jd.id,
    jd.title,
    jd.job_title,
    jd.required_experience_years,
    jd.domain,
    jd.parsing_status,
    COUNT(DISTINCT js.id) as required_skill_count,
    jd.created_at
FROM job_descriptions jd
LEFT JOIN job_skills js ON jd.id = js.job_description_id
GROUP BY jd.id;

-- View for evaluation summary
CREATE VIEW v_evaluation_summary AS
SELECT 
    er.id,
    r.candidate_name,
    jd.job_title,
    er.overall_match_percentage,
    er.recommendation,
    er.technical_skill_score,
    er.experience_score,
    er.created_at
FROM evaluation_results er
JOIN resumes r ON er.resume_id = r.id
JOIN job_descriptions jd ON er.job_description_id = jd.id;

-- ============================================
-- INITIAL DATA (Optional)
-- ============================================

-- Insert default admin user (password: admin123 - change in production!)
INSERT INTO users (email, password_hash, full_name, role) VALUES
('admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lqkkO9QS3TzCjH3rS', 'System Administrator', 'ADMIN');

-- ============================================
-- COMMENTS FOR DOCUMENTATION
-- ============================================

COMMENT ON TABLE resumes IS 'Stores uploaded resume documents and extracted information';
COMMENT ON TABLE job_descriptions IS 'Stores job descriptions and extracted requirements';
COMMENT ON TABLE evaluation_results IS 'Stores AI-powered evaluation results comparing resumes to job descriptions';
COMMENT ON TABLE skill_match_results IS 'Detailed skill-by-skill matching results between resume and JD';
COMMENT ON COLUMN resume_skills.embedding IS 'Vector embedding for semantic skill matching using pgvector';
COMMENT ON COLUMN evaluation_results.recommendation IS 'AI-generated recommendation: Strong Match, Moderate Match, Weak Match, or Reject';
