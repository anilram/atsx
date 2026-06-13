# Backend Folder Structure

```
backend/
├── pom.xml                                          # Maven build configuration
├── docker-compose.yml                               # Local development environment
├── Dockerfile                                       # Container image definition
├── src/
│   ├── main/
│   │   ├── java/com/example/resumeai/
│   │   │   ├── ResumeAiApplication.java            # Spring Boot main class
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java             # Spring Security configuration
│   │   │   │   ├── AiConfig.java                   # Spring AI configuration
│   │   │   │   ├── CorsConfig.java                 # CORS configuration
│   │   │   │   ├── JacksonConfig.java              # JSON serialization config
│   │   │   │   ├── SwaggerConfig.java              # OpenAPI/Swagger config
│   │   │   │   └── VectorStoreConfig.java          # pgvector configuration
│   │   │   ├── controller/
│   │   │   │   ├── ResumeUploadController.java     # Resume upload endpoints
│   │   │   │   ├── JobDescriptionController.java   # JD upload endpoints
│   │   │   │   ├── EvaluationController.java       # Evaluation endpoints
│   │   │   │   ├── ReportController.java           # Report generation endpoints
│   │   │   │   └── HealthController.java           # Health check endpoints
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   │   ├── ResumeUploadRequest.java
│   │   │   │   │   ├── JobDescriptionRequest.java
│   │   │   │   │   ├── EvaluationRequest.java
│   │   │   │   │   └── TextExtractionRequest.java
│   │   │   │   ├── response/
│   │   │   │   │   ├── ResumeUploadResponse.java
│   │   │   │   │   ├── JobDescriptionResponse.java
│   │   │   │   │   ├── EvaluationResultDto.java
│   │   │   │   │   ├── SkillMatchDto.java
│   │   │   │   │   ├── ChartDataDto.java
│   │   │   │   │   ├── PieChartDataDto.java
│   │   │   │   │   ├── BarChartDataDto.java
│   │   │   │   │   └── ApiResponse.java
│   │   │   │   └── domain/
│   │   │   │       ├── ResumeProfileDto.java
│   │   │   │       ├── JobDescriptionProfileDto.java
│   │   │   │       ├── SkillDto.java
│   │   │   │       ├── DegreeDto.java
│   │   │   │       ├── CertificationDto.java
│   │   │   │       ├── AchievementDto.java
│   │   │   │       └── ProjectDto.java
│   │   │   ├── entity/
│   │   │   │   ├── BaseEntity.java                 # Base entity with common fields
│   │   │   │   ├── User.java                       # User entity
│   │   │   │   ├── Resume.java                     # Resume entity
│   │   │   │   ├── JobDescription.java             # Job description entity
│   │   │   │   ├── ResumeSkill.java                # Resume skills
│   │   │   │   ├── JobSkill.java                   # Job required skills
│   │   │   │   ├── ResumeDegree.java               # Resume degrees
│   │   │   │   ├── ResumeCertification.java        # Resume certifications
│   │   │   │   ├── ResumeAchievement.java          # Resume achievements
│   │   │   │   ├── ResumeProject.java              # Resume projects
│   │   │   │   ├── EvaluationResult.java           # Evaluation results
│   │   │   │   ├── SkillMatchResult.java           # Skill matching results
│   │   │   │   ├── ResumeEmbedding.java            # Resume vector embeddings
│   │   │   │   └── JobDescriptionEmbedding.java    # JD vector embeddings
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ResumeRepository.java
│   │   │   │   ├── JobDescriptionRepository.java
│   │   │   │   ├── ResumeSkillRepository.java
│   │   │   │   ├── JobSkillRepository.java
│   │   │   │   ├── ResumeDegreeRepository.java
│   │   │   │   ├── ResumeCertificationRepository.java
│   │   │   │   ├── ResumeAchievementRepository.java
│   │   │   │   ├── ResumeProjectRepository.java
│   │   │   │   ├── EvaluationResultRepository.java
│   │   │   │   ├── SkillMatchResultRepository.java
│   │   │   │   ├── ResumeEmbeddingRepository.java
│   │   │   │   ├── JobDescriptionEmbeddingRepository.java
│   │   │   │   └── custom/
│   │   │   │       └── VectorSearchRepository.java # Custom vector queries
│   │   │   ├── service/
│   │   │   │   ├── parser/
│   │   │   │   │   ├── DocumentExtractionService.java      # Main parser service
│   │   │   │   │   ├── PdfParserService.java               # PDF-specific parsing
│   │   │   │   │   ├── WordParserService.java              # DOC/DOCX parsing
│   │   │   │   │   └── TextCleanerService.java             # Text normalization
│   │   │   │   ├── ai/
│   │   │   │   │   ├── ResumeAIParsingService.java         # Resume AI extraction
│   │   │   │   │   ├── JobDescriptionAIParsingService.java # JD AI extraction
│   │   │   │   │   ├── EmbeddingService.java               # Vector embedding gen
│   │   │   │   │   ├── PromptTemplateService.java          # AI prompt management
│   │   │   │   │   └── LLMClientService.java               # LLM API client
│   │   │   │   ├── scoring/
│   │   │   │   │   ├── MatchScoringService.java            # Main scoring engine
│   │   │   │   │   ├── SkillMatchingService.java           # Skill matching logic
│   │   │   │   │   ├── SkillNormalizationService.java      # Skill normalization
│   │   │   │   │   ├── ExperienceScoringService.java       # Experience scoring
│   │   │   │   │   ├── EducationScoringService.java        # Education scoring
│   │   │   │   │   ├── CertificationScoringService.java    # Certification scoring
│   │   │   │   │   ├── AchievementScoringService.java      # Achievement scoring
│   │   │   │   │   └── DomainScoringService.java           # Domain scoring
│   │   │   │   ├── chart/
│   │   │   │   │   ├── ChartDataService.java               # Chart data aggregation
│   │   │   │   │   ├── PieChartDataService.java            # Pie chart data
│   │   │   │   │   └── BarChartDataService.java            # Bar chart data
│   │   │   │   ├── report/
│   │   │   │   │   ├── ReportGenerationService.java        # PDF report generation
│   │   │   │   │   └── ReportTemplateService.java          # Report templates
│   │   │   │   ├── ResumeService.java                      # Resume business logic
│   │   │   │   ├── JobDescriptionService.java              # JD business logic
│   │   │   │   └── EvaluationService.java                  # Evaluation orchestration
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java             # Global error handler
│   │   │   │   ├── ApiException.java                       # Base API exception
│   │   │   │   ├── DocumentParsingException.java           # Parsing errors
│   │   │   │   ├── AIParsingException.java                 # AI processing errors
│   │   │   │   ├── EvaluationException.java                # Evaluation errors
│   │   │   │   ├── ResourceNotFoundException.java          # 404 errors
│   │   │   │   └── FileUploadException.java                # Upload errors
│   │   │   └── util/
│   │   │       ├── Constants.java                          # Application constants
│   │   │       ├── ValidationUtil.java                     # Validation helpers
│   │   │       ├── JsonUtil.java                           # JSON utilities
│   │   │       ├── FileUtil.java                           # File handling utils
│   │   │       └── ScoreCalculator.java                    # Score calculation utils
│   │   └── resources/
│   │       ├── application.yml                             # Main configuration
│   │       ├── application-dev.yml                         # Development config
│   │       ├── application-prod.yml                        # Production config
│   │       ├── prompts/
│   │       │   ├── resume-extraction-prompt.txt            # Resume AI prompt
│   │       │   └── jd-extraction-prompt.txt                # JD AI prompt
│   │       ├── templates/
│   │       │   └── evaluation-report-template.html         # PDF report template
│   │       └── db/
│   │           └── migration/                              # Flyway migrations
│   │               └── V1__initial_schema.sql
│   └── test/
│       └── java/com/example/resumeai/
│           ├── controller/
│           ├── service/
│           ├── repository/
│           └── integration/
└── .mvn/
    └── wrapper/
```

## Key Configuration Files

### pom.xml (Key Dependencies)
```xml
- Spring Boot 3.2.x
- Spring AI (OpenAI/Anthropic)
- Spring Data JPA
- PostgreSQL Driver
- pgvector-java
- Apache Tika
- Apache PDFBox
- Apache POI
- iText 7 (PDF generation)
- Spring Security + JWT
- MapStruct (DTO mapping)
- Lombok
- Springdoc OpenAPI
- Testcontainers
```

### application.yml Structure
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/resume_ai
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
  
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      chat:
        options:
          model: gpt-4-turbo
    anthropic:
      api-key: ${ANTHROPIC_API_KEY}
      chat:
        options:
          model: claude-3-5-sonnet-20240620

app:
  file-upload:
    max-size: 10MB
    allowed-types: application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document
  scoring:
    weights:
      technical-skills: 0.40
      experience: 0.20
      domain: 0.10
      education: 0.10
      certification: 0.05
      achievement: 0.10
      soft-skills: 0.05
```
