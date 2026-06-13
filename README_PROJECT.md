# Resume AI Evaluation System - Project Summary

## 📋 Documentation Index

This project includes comprehensive design documentation for an AI-powered Resume vs Job Description Evaluation System.

### Generated Documents

| # | Document | Description |
|---|----------|-------------|
| 1 | [HIGH_LEVEL_DESIGN.md](./01_HIGH_LEVEL_DESIGN.md) | Complete system architecture, components, data flows, security, scalability |
| 2 | [BACKEND_FOLDER_STRUCTURE.md](./02_BACKEND_FOLDER_STRUCTURE.md) | Spring Boot package structure, configuration files, dependencies |
| 3 | [FRONTEND_FOLDER_STRUCTURE.md](./03_FRONTEND_FOLDER_STRUCTURE.md) | Angular application structure, components, routing, state management |
| 4 | [DATABASE_SCHEMA.sql](./04_DATABASE_SCHEMA.sql) | PostgreSQL schema with pgvector, tables, indexes, views, triggers |
| 5 | [API_CONTRACT.md](./05_API_CONTRACT.md) | REST API specification with request/response examples |
| 6 | [PLAN_IMPROVEMENTS.md](./06_PLAN_IMPROVEMENTS.md) | Enhancements and recommendations to the original design |

---

## 🎯 System Overview

**Purpose**: AI-powered system that evaluates candidate resumes against job descriptions using hybrid scoring (rule-based + semantic AI matching).

**Key Features**:
- Multi-format document parsing (PDF, DOC, DOCX)
- AI-powered structured data extraction (Claude/OpenAI)
- Semantic skill matching using vector embeddings
- Weighted multi-dimensional scoring
- Interactive visualizations (pie charts, bar charts)
- PDF report generation
- Candidate comparison capabilities

---

## 🏗️ Architecture Highlights

### Technology Stack

**Backend**:
- Java 21 + Spring Boot 3.x
- Spring AI for LLM integration
- PostgreSQL 15+ with pgvector extension
- Apache Tika, PDFBox, POI for document parsing
- iText for PDF generation

**Frontend**:
- Angular 17+
- Angular Material
- Apache ECharts for visualizations
- RxJS for reactive programming

**AI/ML**:
- Claude 3.5 Sonnet (primary) / GPT-4 Turbo (fallback)
- Text embeddings (1536 dimensions)
- Cosine similarity for semantic matching

### Scoring Model

| Dimension | Weight | Method |
|-----------|--------|--------|
| Technical Skills | 40% | Exact + Partial + Semantic |
| Experience | 20% | Rule-based (years comparison) |
| Domain | 10% | Semantic (vector similarity) |
| Education | 10% | Rule-based (degree hierarchy) |
| Certifications | 5% | Exact + Partial matching |
| Achievement Relevance | 10% | AI semantic scoring |
| Soft Skills | 5% | Semantic matching |

**Recommendation Thresholds**:
- Strong Match: ≥ 80%
- Moderate Match: 60-79%
- Weak Match: 40-59%
- Reject: < 40%

---

## 📁 Project Structure

```
/workspace/
├── backend/                          # Spring Boot application
│   ├── src/main/java/com/example/resumeai/
│   │   ├── config/                   # Security, AI, CORS, Swagger configs
│   │   ├── controller/               # REST API endpoints
│   │   ├── dto/                      # Data Transfer Objects
│   │   ├── entity/                   # JPA entities
│   │   ├── repository/               # Data access layer
│   │   ├── service/                  # Business logic
│   │   │   ├── parser/               # Document parsing services
│   │   │   ├── ai/                   # AI/LLM integration services
│   │   │   ├── scoring/              # Scoring engine services
│   │   │   ├── chart/                # Chart data services
│   │   │   └── report/               # PDF report services
│   │   ├── exception/                # Exception handling
│   │   └── util/                     # Utilities
│   └── src/main/resources/
│       ├── prompts/                  # AI prompt templates
│       ├── templates/                # Report templates
│       └── db/migration/             # Flyway migrations
│
├── frontend/                         # Angular application
│   └── src/app/
│       ├── core/                     # Singleton services, guards, interceptors
│       ├── shared/                   # Shared components, pipes, directives
│       └── features/                 # Feature modules
│           ├── dashboard/
│           ├── resume-upload/
│           ├── jd-upload/
│           ├── evaluation-result/
│           ├── candidate-comparison/
│           ├── match-history/
│           └── report-download/
│
└── database/                         # Database scripts
    └── 04_DATABASE_SCHEMA.sql        # Complete PostgreSQL schema
```

---

## 🔌 Key API Endpoints

### Resume Management
```
POST   /api/v1/resumes/upload        # Upload resume file
POST   /api/v1/resumes/text          # Submit resume as text
GET    /api/v1/resumes/{id}          # Get resume details
GET    /api/v1/resumes               # List resumes (paginated)
DELETE /api/v1/resumes/{id}          # Delete resume
```

### Job Description Management
```
POST   /api/v1/jobs/upload           # Upload JD file
POST   /api/v1/jobs/text             # Submit JD as text
GET    /api/v1/jobs/{id}             # Get JD details
GET    /api/v1/jobs                  # List JDs (paginated)
```

### Evaluation
```
POST   /api/v1/evaluations           # Create evaluation
GET    /api/v1/evaluations/{id}      # Get evaluation result
GET    /api/v1/evaluations/history   # Get evaluation history
POST   /api/v1/evaluations/compare   # Compare candidates
```

### Reports
```
GET    /api/v1/reports/{id}/download # Download PDF report
GET    /api/v1/reports/{id}/preview  # Preview HTML report
```

---

## 🗄️ Database Schema Summary

### Core Tables (15 total)

**Base Entities**:
- `users` - Authentication & authorization
- `resumes` - Resume documents & extracted data
- `job_descriptions` - JD documents & extracted data

**Resume Details**:
- `resume_skills` - Skills with embeddings
- `resume_degrees` - Educational qualifications
- `resume_certifications` - Professional certifications
- `resume_achievements` - Notable achievements
- `resume_projects` - Projects with technologies

**JD Details**:
- `job_skills` - Required/preferred skills with embeddings
- `job_education_requirements` - Education requirements
- `job_certification_requirements` - Certification requirements
- `job_soft_skills` - Soft skills
- `job_responsibilities` - Job responsibilities

**Evaluation**:
- `evaluation_results` - Overall evaluation scores
- `skill_match_results` - Detailed skill matching
- `resume_embeddings` - Various resume embeddings
- `job_description_embeddings` - JD embeddings

### Key Features
- **Vector Support**: pgvector extension with 1536-dimension embeddings
- **Performance**: IVFFlat indexes for fast similarity search
- **Data Integrity**: Triggers, constraints, foreign keys with CASCADE
- **Query Optimization**: Materialized views for common queries

---

## 🎨 Frontend Pages

1. **Dashboard** - Overview stats, recent evaluations
2. **Resume Upload** - Drag-and-drop file upload, text input option
3. **Job Description Upload** - File upload or text editor
4. **Evaluation Result** - Detailed analysis with charts
5. **Candidate Comparison** - Side-by-side comparison with radar chart
6. **Match History** - Historical evaluations with filters
7. **Report Download** - PDF report generation and download

### Component Architecture
- **Smart Components**: Handle data fetching and state
- **Dumb Components**: Presentational components with @Input/@Output
- **Shared Components**: Reusable UI components
- **Interceptors**: Error handling, auth, loading indicators

---

## 🔒 Security Features

- JWT-based authentication with refresh tokens
- Role-based access control (ADMIN, RECRUITER, CANDIDATE)
- File upload validation (magic bytes, MIME type, size limits)
- TLS 1.3 for data in transit
- AES-256 encryption for sensitive data at rest
- Rate limiting on all endpoints
- CORS configuration
- SQL injection prevention via JPA
- XSS protection via Angular sanitization

---

## ⚡ Performance Optimizations

### Caching Strategy
- **L1**: Caffeine (in-memory, 5 min TTL)
- **L2**: Redis (distributed, 30 min TTL)
- **L3**: Database materialized views

### Async Processing
- Resume/JD parsing with status polling
- Background embedding generation
- Async report generation with notifications

### Database Optimization
- HikariCP connection pooling (max 20)
- Batch inserts for bulk operations
- IVFFlat vector indexes
- Full-text search indexes
- Query result caching

---

## 🧪 Testing Strategy

```
Test Pyramid:
- Unit Tests (JUnit 5 + Mockito): 70% coverage target
- Integration Tests (Testcontainers): 20%
- E2E Tests (Cypress): 10%
```

**Test Infrastructure**:
- Testcontainers for PostgreSQL with pgvector
- WireMock for AI API mocking
- Factory patterns for test data
- Database migration testing

---

## 🚀 Deployment

### Development (Docker Compose)
```yaml
Services:
  - postgres (with pgvector)
  - backend (Spring Boot)
  - frontend (Angular)
  - redis (caching)
  - prometheus (metrics)
  - grafana (dashboards)
```

### Production (Kubernetes)
- Deployments with resource limits
- HorizontalPodAutoscaler
- ConfigMaps and Secrets
- Ingress controller
- PersistentVolumeClaims
- Service mesh ready

---

## 📊 Monitoring & Observability

**Metrics** (Micrometer + Prometheus):
- Resume upload rate
- Evaluation duration
- AI request latency
- Skill match accuracy
- Active evaluations

**Logging** (SLF4J + Logback → ELK):
- Structured JSON logging
- Correlation IDs
- Audit logs

**Tracing** (Spring Cloud Sleuth + Zipkin):
- Distributed tracing
- AI call spans
- Database query tracing

**Health Checks**:
- Database connectivity
- AI service status
- Disk space monitoring

---

## 🔄 Implementation Roadmap

### Phase 1: Core Functionality (Current)
- [x] High-level design
- [x] Backend architecture
- [x] Frontend architecture
- [x] Database schema
- [x] API contracts
- [ ] Backend implementation
- [ ] Frontend implementation
- [ ] Integration testing

### Phase 2: Enhanced Features (Q2 2025)
- [ ] Multi-language support
- [ ] Video interview analysis
- [ ] JD optimization suggestions
- [ ] Email notifications

### Phase 3: Integrations (H2 2025)
- [ ] ATS integrations (Greenhouse, Lever)
- [ ] Blockchain credential verification
- [ ] Candidate ranking across JDs
- [ ] Mobile app (React Native)

### Phase 4: Advanced Features (2026)
- [ ] Real-time collaboration
- [ ] Predictive hiring success modeling
- [ ] Advanced analytics dashboard
- [ ] Background check integrations

---

## 📝 Next Steps

To begin implementation:

1. **Setup Development Environment**
   ```bash
   # Install prerequisites
   - Java 21 JDK
   - Node.js 18+
   - Docker & Docker Compose
   - PostgreSQL 15+ with pgvector
   
   # Clone and setup
   cd backend && mvn clean install
   cd frontend && npm install
   docker-compose up -d
   ```

2. **Configure Environment Variables**
   ```bash
   # Backend (.env)
   DATABASE_URL=jdbc:postgresql://localhost:5432/resume_ai
   OPENAI_API_KEY=your_key_here
   ANTHROPIC_API_KEY=your_key_here
   JWT_SECRET=your_secret_here
   ```

3. **Run Database Migrations**
   ```bash
   psql -U postgres -d resume_ai -f 04_DATABASE_SCHEMA.sql
   ```

4. **Start Backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

5. **Start Frontend**
   ```bash
   cd frontend
   ng serve --proxy-config proxy.conf.json
   ```

6. **Access Application**
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8080/api/v1
   - API Docs: http://localhost:8080/swagger-ui.html

---

## 📞 Support & Documentation

- **API Documentation**: Swagger UI at `/swagger-ui.html`
- **Database Schema**: See `04_DATABASE_SCHEMA.sql` for detailed comments
- **Error Codes**: Refer to `05_API_CONTRACT.md` section on error responses
- **Prompt Templates**: Located in `backend/src/main/resources/prompts/`

---

## ✅ Success Criteria

The system will be considered successful when it achieves:

1. **Accuracy**: >85% correlation with human recruiter evaluations
2. **Performance**: <3 seconds average evaluation time
3. **Reliability**: 99.9% uptime SLA
4. **Scalability**: Handle 1000+ concurrent evaluations
5. **User Satisfaction**: >4.5/5 user rating

---

**Document Version**: 1.0  
**Last Updated**: 2024  
**Status**: Ready for Implementation
