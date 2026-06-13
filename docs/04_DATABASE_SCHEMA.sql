-- Enable pgvector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- Create enum types for consistent values
CREATE TYPE skill_category AS ENUM ('Backend', 'Frontend', 'Cloud', 'DevOps', 'Database', 'Testing', 'AI', 'Other');
CREATE TYPE skill_level AS ENUM ('Beginner', 'Intermediate', 'Advanced', 'Expert');
CREATE TYPE match_type AS ENUM ('Exact', 'Partial', 'Semantic', 'Missing');
CREATE TYPE recommendation_type AS ENUM ('Strong Match', 'Moderate Match', 'Weak Match', 'Reject');
CREATE TYPE evaluation_status AS ENUM ('PENDING', 'PROCESSING', 'COMPLETED', 'FAILED');

-- Base table for audit fields
CREATE TABLE base_entity (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resumes table
CREATE TABLE resumes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500),
    file_size BIGINT,
    content_type VARCHAR(100),
    raw_text TEXT,
    candidate_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    total_experience_years NUMERIC(4,2),
    current_role VARCHAR(255),
    summary TEXT,
    status VARCHAR(50) DEFAULT 'UPLOADED',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Descriptions table
CREATE TABLE job_descriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    file_name VARCHAR(255),
    file_path VARCHAR(500),
    file_size BIGINT,
    content_type VARCHAR(100),
    raw_text TEXT,
    job_title VARCHAR(255),
    required_experience_years NUMERIC(4,2),
    domain VARCHAR(255),
    status VARCHAR(50) DEFAULT 'DRAFT',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Skills table
CREATE TABLE resume_skills (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    category skill_category DEFAULT 'Other',
    years_experience NUMERIC(4,2),
    level skill_level DEFAULT 'Intermediate',
    embedding vector(1536),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Skills table
CREATE TABLE job_skills (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    weight INTEGER DEFAULT 1,
    skill_type VARCHAR(50) DEFAULT 'REQUIRED', -- REQUIRED or PREFERRED
    embedding vector(1536),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Degrees table
CREATE TABLE resume_degrees (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    degree VARCHAR(255) NOT NULL,
    university VARCHAR(255),
    graduation_year VARCHAR(10),
    field_of_study VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Certifications table
CREATE TABLE resume_certifications (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    issuer VARCHAR(255),
    year VARCHAR(10),
    credential_id VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Achievements table
CREATE TABLE resume_achievements (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    impact TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Projects table
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

-- Job Responsibilities table
CREATE TABLE job_responsibilities (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    description TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Education Requirements table
CREATE TABLE job_education_requirements (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    requirement VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Certification Requirements table
CREATE TABLE job_certification_requirements (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    requirement VARCHAR(255) NOT NULL,
    is_mandatory BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Soft Skills table
CREATE TABLE job_soft_skills (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    skill VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Evaluation Results table
CREATE TABLE evaluation_results (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    overall_match_percentage NUMERIC(5,2),
    recommendation recommendation_type,
    high_level_summary TEXT,
    technical_skill_score NUMERIC(5,2),
    experience_score NUMERIC(5,2),
    domain_score NUMERIC(5,2),
    education_score NUMERIC(5,2),
    certification_score NUMERIC(5,2),
    achievement_score NUMERIC(5,2),
    soft_skill_score NUMERIC(5,2),
    strengths TEXT[],
    improvements TEXT[],
    status evaluation_status DEFAULT 'PENDING',
    evaluated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Skill Match Results table
CREATE TABLE skill_match_results (
    id BIGSERIAL PRIMARY KEY,
    evaluation_result_id BIGINT NOT NULL REFERENCES evaluation_results(id) ON DELETE CASCADE,
    resume_skill_id BIGINT REFERENCES resume_skills(id),
    job_skill_id BIGINT REFERENCES job_skills(id),
    match_type match_type NOT NULL,
    similarity_score NUMERIC(5,4),
    resume_skill_name VARCHAR(255) NOT NULL,
    job_skill_name VARCHAR(255) NOT NULL,
    explanation TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Resume Embeddings table (for semantic search)
CREATE TABLE resume_embeddings (
    id BIGSERIAL PRIMARY KEY,
    resume_id BIGINT NOT NULL REFERENCES resumes(id) ON DELETE CASCADE,
    embedding_type VARCHAR(50) NOT NULL, -- SUMMARY, SKILLS, EXPERIENCE
    embedding vector(1536),
    metadata JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Job Description Embeddings table
CREATE TABLE job_description_embeddings (
    id BIGSERIAL PRIMARY KEY,
    job_description_id BIGINT NOT NULL REFERENCES job_descriptions(id) ON DELETE CASCADE,
    embedding_type VARCHAR(50) NOT NULL,
    embedding vector(1536),
    metadata JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for performance
CREATE INDEX idx_resumes_user_id ON resumes(user_id);
CREATE INDEX idx_resumes_status ON resumes(status);
CREATE INDEX idx_job_descriptions_user_id ON job_descriptions(user_id);
CREATE INDEX idx_resume_skills_resume_id ON resume_skills(resume_id);
CREATE INDEX idx_resume_skills_category ON resume_skills(category);
CREATE INDEX idx_job_skills_job_id ON job_skills(job_description_id);
CREATE INDEX idx_evaluation_results_resume_id ON evaluation_results(resume_id);
CREATE INDEX idx_evaluation_results_job_id ON evaluation_results(job_description_id);
CREATE INDEX idx_skill_match_results_evaluation_id ON skill_match_results(evaluation_result_id);

-- Create pgvector indexes for similarity search
CREATE INDEX idx_resume_skills_embedding ON resume_skills USING ivfflat (embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_job_skills_embedding ON job_skills USING ivfflat (embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_resume_embeddings_vector ON resume_embeddings USING ivfflat (embedding vector_cosine_ops) WITH (lists = 100);
CREATE INDEX idx_job_description_embeddings_vector ON job_description_embeddings USING ivfflat (embedding vector_cosine_ops) WITH (lists = 100);

-- Create function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers for auto-updating updated_at
CREATE TRIGGER update_resumes_updated_at BEFORE UPDATE ON resumes
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_job_descriptions_updated_at BEFORE UPDATE ON job_descriptions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_evaluation_results_updated_at BEFORE UPDATE ON evaluation_results
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Insert sample data for testing
INSERT INTO users (email, password_hash, full_name, role) VALUES
('admin@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Admin User', 'ADMIN'),
('recruiter@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Recruiter User', 'RECRUITER');

COMMENT ON TABLE resumes IS 'Stores candidate resume information and extracted data';
COMMENT ON TABLE job_descriptions IS 'Stores job description requirements';
COMMENT ON TABLE evaluation_results IS 'Stores AI-powered evaluation results between resume and JD';
COMMENT ON TABLE skill_match_results IS 'Stores detailed skill matching results with explanations';
