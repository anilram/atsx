# AI-Based Resume vs Job Description Evaluation System

A production-ready, AI-powered system that evaluates candidate resumes against job descriptions using Spring Boot 3.x, Spring AI, Angular, and PostgreSQL with pgvector.

## Features

- **Document Parsing**: PDF, DOC, DOCX support using Apache Tika, PDFBox, POI
- **AI-Powered Extraction**: Structured data extraction using OpenAI/Claude LLMs
- **Semantic Matching**: Vector embeddings for skill similarity matching
- **Hybrid Scoring**: Rule-based + AI scoring model
- **Visualizations**: Pie charts and bar charts for skill distribution
- **PDF Reports**: Professional evaluation reports using iText
- **REST API**: Comprehensive API with Swagger documentation

## Tech Stack

### Backend
- Java 21
- Spring Boot 3.2.1
- Spring AI (OpenAI & Anthropic)
- Spring Security
- PostgreSQL with pgvector
- Apache Tika, PDFBox, POI
- iText for PDF generation
- MapStruct for DTO mapping
- Lombok

### Frontend
- Angular 17+
- Angular Material
- Apache ECharts
- RxJS

## Project Structure

```
/workspace
├── backend/                 # Spring Boot application
│   ├── src/main/java/...
│   ├── src/main/resources/
│   └── pom.xml
├── frontend/                # Angular application (to be implemented)
└── docs/                    # Documentation
    ├── 01_HIGH_LEVEL_DESIGN.md
    ├── 02_BACKEND_FOLDER_STRUCTURE.md
    ├── 03_FRONTEND_FOLDER_STRUCTURE.md
    ├── 04_DATABASE_SCHEMA.sql
    └── 05_API_CONTRACT.md
```

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.8+
- PostgreSQL 15+ with pgvector extension
- Node.js 18+ (for frontend)
- OpenAI or Anthropic API key

### Database Setup

```bash
# Create database
createdb resume_ai_db

# Enable pgvector extension
psql -d resume_ai_db -c "CREATE EXTENSION IF NOT EXISTS vector;"

# Run schema
psql -d resume_ai_db -f docs/04_DATABASE_SCHEMA.sql
```

### Backend Configuration

Create `.env` file in backend root:
```bash
OPENAI_API_KEY=your-openai-key
ANTHROPIC_API_KEY=your-anthropic-key
JWT_SECRET=your-secret-key-change-in-production
```

### Run Backend

```bash
cd backend
mvn spring-boot:run
```

Access Swagger UI at: http://localhost:8080/swagger-ui.html

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/resumes/upload` | Upload resume file |
| POST | `/api/v1/resumes/text` | Submit resume as text |
| POST | `/api/v1/jobs/upload` | Upload JD file |
| POST | `/api/v1/jobs/text` | Create JD from text |
| POST | `/api/v1/evaluations` | Evaluate resume vs JD |
| GET | `/api/v1/evaluations/{id}` | Get evaluation result |
| GET | `/api/v1/evaluations/history` | Get evaluation history |
| GET | `/api/v1/reports/{id}/download` | Download PDF report |

## Scoring Model

Overall Match = (TechnicalSkills × 0.40) + (Experience × 0.20) + (Domain × 0.10) + (Education × 0.10) + (Certifications × 0.05) + (Achievements × 0.10) + (SoftSkills × 0.05)

### Recommendations
- **Strong Match**: ≥ 80%
- **Moderate Match**: 60-79%
- **Weak Match**: 40-59%
- **Reject**: < 40%

## Documentation

- [High Level Design](docs/01_HIGH_LEVEL_DESIGN.md)
- [Backend Structure](docs/02_BACKEND_FOLDER_STRUCTURE.md)
- [Frontend Structure](docs/03_FRONTEND_FOLDER_STRUCTURE.md)
- [Database Schema](docs/04_DATABASE_SCHEMA.sql)
- [API Contract](docs/05_API_CONTRACT.md)

## Next Steps

1. Implement full document parsing service
2. Complete AI extraction services
3. Implement skill matching algorithms
4. Build Angular frontend components
5. Add authentication & authorization
6. Implement PDF report generation
7. Add comprehensive tests

## License

MIT License
