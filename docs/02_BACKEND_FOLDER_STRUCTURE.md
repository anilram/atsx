# Backend Folder Structure

```
backend/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/resumeai/
│   │   │   ├── ResumeAiApplication.java
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SpringAIConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   ├── CorsConfig.java
│   │   │   │   └── AsyncConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── ResumeController.java
│   │   │   │   ├── JobDescriptionController.java
│   │   │   │   ├── EvaluationController.java
│   │   │   │   └── ReportController.java
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
│   │   │   │   │   └── BarChartDataDto.java
│   │   │   │   └── profile/
│   │   │   │       ├── ResumeProfileDto.java
│   │   │   │       ├── JobDescriptionProfileDto.java
│   │   │   │       ├── SkillDto.java
│   │   │   │       ├── DegreeDto.java
│   │   │   │       ├── CertificationDto.java
│   │   │   │       ├── AchievementDto.java
│   │   │   │       └── ProjectDto.java
│   │   │   ├── entity/
│   │   │   │   ├── BaseEntity.java
│   │   │   │   ├── User.java
│   │   │   │   ├── Resume.java
│   │   │   │   ├── JobDescription.java
│   │   │   │   ├── ResumeSkill.java
│   │   │   │   ├── JobSkill.java
│   │   │   │   ├── ResumeDegree.java
│   │   │   │   ├── ResumeCertification.java
│   │   │   │   ├── ResumeAchievement.java
│   │   │   │   ├── ResumeProject.java
│   │   │   │   ├── JobResponsibility.java
│   │   │   │   ├── EvaluationResult.java
│   │   │   │   ├── SkillMatchResult.java
│   │   │   │   ├── ResumeEmbedding.java
│   │   │   │   └── JobDescriptionEmbedding.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ResumeRepository.java
│   │   │   │   ├── JobDescriptionRepository.java
│   │   │   │   ├── ResumeSkillRepository.java
│   │   │   │   ├── JobSkillRepository.java
│   │   │   │   ├── ResumeDegreeRepository.java
│   │   │   │   ├── ResumeCertificationRepository.java
│   │   │   │   ├── ResumeAchievementRepository.java
│   │   │   │   ├── EvaluationResultRepository.java
│   │   │   │   ├── SkillMatchResultRepository.java
│   │   │   │   └── embedding/
│   │   │   │       ├── ResumeEmbeddingRepository.java
│   │   │   │       └── JobDescriptionEmbeddingRepository.java
│   │   │   ├── service/
│   │   │   │   ├── DocumentExtractionService.java
│   │   │   │   ├── ai/
│   │   │   │   │   ├── ResumeAIParsingService.java
│   │   │   │   │   ├── JobDescriptionAIParsingService.java
│   │   │   │   │   ├── EmbeddingService.java
│   │   │   │   │   └── AIServiceProvider.java
│   │   │   │   ├── parser/
│   │   │   │   │   ├── PdfParserService.java
│   │   │   │   │   ├── DocParserService.java
│   │   │   │   │   └── TextCleanerService.java
│   │   │   │   ├── scoring/
│   │   │   │   │   ├── SkillNormalizationService.java
│   │   │   │   │   ├── SkillMatchingService.java
│   │   │   │   │   ├── MatchScoringService.java
│   │   │   │   │   └── ScoringStrategy.java
│   │   │   │   ├── chart/
│   │   │   │   │   └── ChartDataService.java
│   │   │   │   └── report/
│   │   │   │       └── ReportGenerationService.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── FileUploadException.java
│   │   │   │   ├── AIParsingException.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── util/
│   │   │       ├── JsonUtil.java
│   │   │       ├── FileUtil.java
│   │   │       ├── ExperienceCalculator.java
│   │   │       └── Constants.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       ├── prompts/
│   │       │   ├── resume-extraction-prompt.txt
│   │       │   └── jd-extraction-prompt.txt
│   │       └── db/
│   │           └── migration/
│   │               └── V1__initial_schema.sql
│   └── test/
│       └── java/com/example/resumeai/
│           ├── controller/
│           ├── service/
│           └── integration/
```

## Package Descriptions

### config
- Security configuration (JWT, OAuth2)
- Spring AI bean configuration
- Swagger/OpenAPI documentation
- CORS settings
- Async task configuration

### controller
- REST API endpoints
- Request validation
- Response mapping
- File upload handling

### dto
- **request**: Incoming request objects
- **response**: Outgoing response objects
- **profile**: Structured data from AI parsing

### entity
- JPA entities with Hibernate mappings
- pgvector column support
- Audit fields (@CreatedDate, @LastModifiedDate)

### repository
- Spring Data JPA repositories
- Custom queries for vector similarity
- Native SQL for pgvector operations

### service
- **ai**: LLM integration, embeddings
- **parser**: Document text extraction
- **scoring**: Match calculation logic
- **chart**: Data aggregation for visualizations
- **report**: PDF generation

### exception
- Custom exceptions
- Global exception handler
- Error response formatting

### util
- Helper classes
- Constants
- File operations
- JSON utilities
