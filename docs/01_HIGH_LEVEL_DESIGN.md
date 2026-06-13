# AI-Based Resume vs Job Description Evaluation System

## High-Level Design (HLD)

### 1. System Overview
The system follows a modular, layered architecture designed for high throughput, maintainability, and seamless AI integration.

### 2. Architecture Layers

#### Presentation Layer (Angular)
- Responsive Single Page Application (SPA)
- Angular Material for UI components
- Apache ECharts for interactive data visualizations (Pie/Bar charts)
- Lazy-loaded feature modules
- Smart/Dumb component pattern

#### API Gateway / Controller Layer (Spring Boot)
- RESTful endpoints for file uploads, text inputs, evaluation triggers, and report downloads
- Multipart request handling and validation
- Spring Security for authentication/authorization
- Global exception handling

#### Business Logic / Service Layer

**Document Parser Services:**
- `DocumentExtractionService`: Main entry point for document parsing
- `ResumeParserService`: PDF/DOC/DOCX text extraction using Apache Tika, PDFBox, POI
- `JobDescriptionParserService`: JD document text extraction

**AI Extraction Services (Spring AI):**
- `ResumeAIParsingService`: Extracts structured resume data with strict JSON schema
- `JobDescriptionAIParsingService`: Extracts structured JD requirements
- `EmbeddingService`: Generates vector embeddings for semantic matching

**Scoring Engine:**
- `SkillNormalizationService`: Normalizes skill names and categories
- `SkillMatchingService`: Exact, partial, and semantic matching
- `MatchScoringService`: Hybrid scoring (rule-based + semantic)
- Weighted scoring model implementation

**Chart & Report Services:**
- `ChartDataService`: Aggregates data for visualizations
- `ReportGenerationService`: PDF report generation using iText

#### Data Layer (PostgreSQL + pgvector)
- Relational tables for structured data
- Vector type for AI embeddings
- IVFFlat indexes for fast semantic search

### 3. Hybrid Scoring Model

**Overall JD Match Formula:**
```
Overall Score = (TechnicalSkills × 0.40) + 
                (Experience × 0.20) + 
                (Domain × 0.10) + 
                (Education × 0.10) + 
                (Certifications × 0.05) + 
                (Achievements × 0.10) + 
                (SoftSkills × 0.05)
```

**Score Breakdown:**
- **Technical Skills (40%)**: Exact, partial, and semantic vector similarity
- **Experience (20%)**: Years comparison with logarithmic scaling
- **Domain (10%)**: Semantic similarity of roles/projects
- **Education (10%)**: Degree level matching
- **Certifications (5%)**: Required vs held certs
- **Achievement Relevance (10%)**: AI semantic scoring
- **Soft Skills (5%)**: Semantic matching

### 4. Key Features
- Multi-format document support (PDF, DOC, DOCX)
- OCR-ready architecture
- Three-tier caching strategy
- Async processing for long-running evaluations
- Audit logging and versioning
- Docker Compose for local development
- Kubernetes-ready structure
