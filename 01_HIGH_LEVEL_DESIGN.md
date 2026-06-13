# AI-Based Resume vs Job Description Evaluation System
## High-Level Design (HLD)

### 1. Executive Summary

This document outlines the architecture for an AI-powered Resume vs Job Description Evaluation System that leverages Spring Boot, Spring AI, Angular, and PostgreSQL with pgvector to provide intelligent candidate matching and analysis.

### 2. Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────┐
│                           PRESENTATION LAYER                             │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                    Angular 17+ SPA                               │    │
│  │  - Angular Material Components                                   │    │
│  │  - Apache ECharts for Visualizations                             │    │
│  │  - Reactive Forms & RxJS                                         │    │
│  │  - Lazy-loaded Feature Modules                                   │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    │ HTTPS/REST
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                           API GATEWAY LAYER                              │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │               Spring Boot 3.x REST Controllers                   │    │
│  │  - ResumeUploadController                                        │    │
│  │  - JobDescriptionController                                      │    │
│  │  - EvaluationController                                          │    │
│  │  - ReportController                                              │    │
│  │  - Spring Security + JWT Authentication                          │    │
│  │  - Global Exception Handler                                      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                        BUSINESS LOGIC LAYER                              │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐      │
│  │  Parser Service  │  │   AI Service     │  │  Scoring Engine  │      │
│  │  - Apache Tika   │  │  - Spring AI     │  │  - Rule-based    │      │
│  │  - PDFBox        │  │  - LLM Prompts   │  │  - Semantic      │      │
│  │  - Apache POI    │  │  - JSON Schema   │  │  - Weighted      │      │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘      │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐      │
│  │ Embedding Service│  │  Chart Service   │  │  Report Service  │      │
│  │  - Vector Gen    │  │  - Pie Chart     │  │  - iText PDF     │      │
│  │  - pgvector      │  │  - Bar Chart     │  │  - Templates     │      │
│  │  - Cosine Sim    │  │  - Data Agg      │  │  - Download      │      │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘      │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                           DATA ACCESS LAYER                              │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │              Spring Data JPA Repositories                        │    │
│  │  - Entity Management                                             │    │
│  │  - Custom Queries                                                │    │
│  │  - Vector Similarity Search                                      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         PERSISTENCE LAYER                                │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                  PostgreSQL 15+ with pgvector                    │    │
│  │  - Relational Tables (Users, Resumes, JDs, Evaluations)         │    │
│  │  - Vector Embeddings (Resume, JD, Skills)                       │    │
│  │  - Full-text Search Indexes                                     │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### 3. Core Components

#### 3.1 Document Parsing Module
**Purpose**: Extract raw text from uploaded documents (PDF, DOC, DOCX)

**Technologies**:
- Apache Tika (unified document parsing)
- Apache PDFBox (PDF-specific extraction)
- Apache POI (Word document parsing)
- Optional: Tesseract OCR for scanned documents

**Flow**:
```
Upload → MimeType Validation → Text Extraction → Cleaning → Normalization
```

#### 3.2 AI Extraction Module
**Purpose**: Convert unstructured text into structured JSON using LLMs

**Components**:
- **ResumeAIParsingService**: Extracts candidate profile
- **JobDescriptionAIParsingService**: Extracts job requirements
- **Spring AI Integration**: Manages LLM connections (Claude/OpenAI)
- **Prompt Templates**: Enforce strict JSON schema output

**AI Models**:
- Primary: Claude 3.5 Sonnet (via Anthropic API)
- Fallback: GPT-4 Turbo (via OpenAI API)
- Embeddings: text-embedding-3-large or similar

#### 3.3 Embedding & Vector Search Module
**Purpose**: Enable semantic matching beyond exact keyword matching

**Implementation**:
- Generate embeddings for skills, experience descriptions, and domains
- Store vectors in PostgreSQL using pgvector extension
- Perform cosine similarity calculations for semantic matching

**Vector Dimensions**:
- Skill embeddings: 1536 dimensions
- Experience embeddings: 1536 dimensions
- Domain embeddings: 1536 dimensions

#### 3.4 Hybrid Scoring Engine
**Purpose**: Calculate comprehensive match scores using multiple strategies

**Scoring Dimensions**:

| Dimension | Weight | Method |
|-----------|--------|--------|
| Technical Skills | 40% | Exact + Partial + Semantic (Vector) |
| Experience | 20% | Rule-based (Years comparison) |
| Domain | 10% | Semantic (Vector similarity) |
| Education | 10% | Rule-based (Degree hierarchy) |
| Certifications | 5% | Exact + Partial matching |
| Achievement Relevance | 10% | AI Semantic scoring |
| Soft Skills | 5% | Semantic matching |

**Scoring Flow**:
```
1. Extract Skills from Resume & JD
2. Normalize Skills (synonyms, variations)
3. Calculate Exact Matches (30% of skill score)
4. Calculate Partial Matches (20% of skill score)
5. Calculate Semantic Matches (50% of skill score)
6. Apply Weights across all dimensions
7. Generate Final Score (0-100)
8. Determine Recommendation (Strong/Moderate/Weak/Reject)
```

#### 3.5 Skill Normalization Service
**Purpose**: Handle skill name variations and synonyms

**Features**:
- Synonym mapping (e.g., "JS" → "JavaScript")
- Category classification (Backend, Frontend, Cloud, etc.)
- Level normalization (Beginner, Intermediate, Advanced, Expert)
- Technology grouping (e.g., "Spring Boot" ∈ "Java Frameworks")

#### 3.6 Chart Data Service
**Purpose**: Transform evaluation results into visualization-ready formats

**Outputs**:
- **Pie Chart**: Skill distribution by category
- **Bar Chart**: Skills with experience timeline
- **Radar Chart** (optional): Multi-dimensional score comparison

#### 3.7 Report Generation Service
**Purpose**: Create downloadable PDF reports

**Features**:
- Professional template design
- Embedded charts and graphs
- Detailed score breakdowns
- Strengths and improvement areas
- Company branding support

### 4. Data Flow Diagrams

#### 4.1 Resume Upload & Processing Flow
```
User Upload → Controller → Validation → Parser Service → Raw Text
                                                      ↓
                                              AI Parsing Service
                                                      ↓
                                            Structured Resume JSON
                                                      ↓
                                          Embedding Generation
                                                      ↓
                                           Database Persistence
                                                      ↓
                                            Response to Client
```

#### 4.2 Evaluation Flow
```
Evaluation Request → Fetch Resume & JD Profiles
                          ↓
              Skill Matching Service
                          ↓
              Scoring Engine (Hybrid)
                          ↓
              Chart Data Aggregation
                          ↓
              Evaluation Result DTO
                          ↓
              Database Persistence
                          ↓
              Response to Client
```

#### 4.3 Report Generation Flow
```
Report Request → Fetch Evaluation Result
                      ↓
              Load PDF Template
                      ↓
              Populate Fields & Charts
                      ↓
              Generate PDF Binary
                      ↓
              Stream to Client
```

### 5. Security Considerations

- **Authentication**: JWT-based authentication with Spring Security
- **Authorization**: Role-based access control (Admin, Recruiter, Candidate)
- **Data Encryption**: TLS 1.3 for transit, AES-256 for sensitive data at rest
- **File Upload Security**: 
  - File type validation (magic bytes, not just extension)
  - File size limits (max 10MB)
  - Virus scanning integration point
- **API Rate Limiting**: Prevent abuse via request throttling
- **Audit Logging**: Track all evaluation activities

### 6. Scalability Strategy

#### Horizontal Scaling
- Stateless service design for easy replication
- Redis cache for frequently accessed data
- Database connection pooling (HikariCP)
- Async processing for heavy AI operations

#### Performance Optimization
- Caching layers:
  - L1 Cache: Caffeine for in-memory caching
  - L2 Cache: Redis for distributed caching
- Database indexing on frequently queried columns
- Vector index using pgvector's IVFFlat for fast similarity search
- CDN for static assets (Angular build)

### 7. Deployment Architecture

#### Development (Docker Compose)
```yaml
Services:
  - postgres (with pgvector)
  - backend (Spring Boot)
  - frontend (Angular dev server)
  - redis (optional caching)
```

#### Production (Kubernetes)
```
┌─────────────────────────────────────────┐
│           Kubernetes Cluster             │
│  ┌────────────────────────────────────┐ │
│  │         Ingress Controller         │ │
│  └────────────────────────────────────┘ │
│                  │                       │
│  ┌───────────────▼────────────────┐     │
│  │      Frontend Deployment        │     │
│  │      (Angular - 3 replicas)     │     │
│  └─────────────────────────────────┘     │
│                  │                       │
│  ┌───────────────▼────────────────┐     │
│  │      Backend Deployment         │     │
│  │      (Spring Boot - 3 replicas) │     │
│  └─────────────────────────────────┘     │
│         │              │                 │
│  ┌──────▼────┐   ┌─────▼──────┐         │
│  │  Redis    │   │ PostgreSQL │         │
│  │  Stateful │   │ Stateful   │         │
│  └───────────┘   └────────────┘         │
└─────────────────────────────────────────┘
```

### 8. Monitoring & Observability

- **Metrics**: Micrometer + Prometheus
- **Logging**: SLF4J + Logback → ELK Stack
- **Tracing**: Spring Cloud Sleuth + Zipkin/Jaeger
- **Health Checks**: Spring Boot Actuator endpoints
- **Alerting**: Grafana alerts on key metrics

### 9. Error Handling Strategy

- Global exception handler with standardized error responses
- Graceful degradation for AI service failures
- Retry mechanisms with exponential backoff
- Circuit breaker pattern for external APIs (Resilience4j)
- Comprehensive logging with correlation IDs

### 10. Testing Strategy

- **Unit Tests**: JUnit 5 + Mockito (80% coverage target)
- **Integration Tests**: Testcontainers for PostgreSQL
- **API Tests**: Spring MVC Test framework
- **E2E Tests**: Cypress for Angular frontend
- **Load Tests**: Gatling for performance validation

### 11. Future Enhancements

- Multi-language resume support
- Video interview analysis integration
- Automated job description optimization suggestions
- Candidate ranking across multiple JDs
- Integration with ATS systems (Greenhouse, Lever)
- Blockchain-based credential verification
