# API Contract Specification

## Base URL
```
/api/v1
```

## Authentication
All endpoints require Bearer token authentication (JWT).
```
Authorization: Bearer <token>
```

---

## 1. Resume Controller

### Upload Resume File
**Endpoint:** `POST /resumes/upload`  
**Content-Type:** `multipart/form-data`

**Request:**
```
file: (binary) PDF/DOC/DOCX file
```

**Response:** `200 OK`
```json
{
  "resumeId": 1,
  "fileName": "john_doe_resume.pdf",
  "fileSize": 245678,
  "contentType": "application/pdf",
  "status": "PARSED",
  "candidateName": "John Doe",
  "uploadedAt": "2024-01-15T10:30:00Z"
}
```

### Upload Resume as Text
**Endpoint:** `POST /resumes/text`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "text": "John Doe\nSenior Java Developer\nExperience: 8 years..."
}
```

**Response:** `200 OK`
```json
{
  "resumeId": 1,
  "fileName": "text_input.txt",
  "status": "PARSED",
  "candidateName": "John Doe",
  "uploadedAt": "2024-01-15T10:30:00Z"
}
```

### Get Resume by ID
**Endpoint:** `GET /resumes/{id}`

**Response:** `200 OK`
```json
{
  "id": 1,
  "candidateName": "John Doe",
  "email": "john.doe@email.com",
  "phone": "+1-555-0123",
  "totalExperienceYears": 8.5,
  "currentRole": "Senior Java Developer",
  "summary": "Experienced software developer...",
  "skills": [
    {
      "name": "Java",
      "category": "Backend",
      "years": 8,
      "level": "Expert"
    }
  ],
  "degrees": [
    {
      "degree": "Bachelor of Science in Computer Science",
      "university": "MIT",
      "year": "2015"
    }
  ],
  "certifications": [
    {
      "name": "AWS Certified Solutions Architect",
      "issuer": "Amazon Web Services",
      "year": "2022"
    }
  ],
  "achievements": [
    {
      "title": "Led migration to microservices",
      "description": "Migrated monolithic application",
      "impact": "Reduced deployment time by 60%"
    }
  ]
}
```

### List All Resumes
**Endpoint:** `GET /resumes`  
**Query Params:** `page`, `size`, `sort`

**Response:** `200 OK`
```json
{
  "content": [...],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 25,
  "totalPages": 3,
  "last": false,
  "first": true,
  "numberOfElements": 10,
  "size": 10,
  "number": 0
}
```

### Delete Resume
**Endpoint:** `DELETE /resumes/{id}`

**Response:** `204 No Content`

---

## 2. Job Description Controller

### Create JD from File
**Endpoint:** `POST /jobs/upload`  
**Content-Type:** `multipart/form-data`

**Request:**
```
file: (binary) PDF/DOC/DOCX file
title: (string) Job title
```

**Response:** `200 OK`
```json
{
  "jobDescriptionId": 1,
  "title": "Senior Java Developer Position",
  "fileName": "jd_senior_java.pdf",
  "status": "PARSED",
  "createdAt": "2024-01-15T11:00:00Z"
}
```

### Create JD from Text
**Endpoint:** `POST /jobs/text`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "title": "Senior Java Developer Position",
  "text": "We are looking for a Senior Java Developer with 5+ years of experience..."
}
```

**Response:** `200 OK`
```json
{
  "jobDescriptionId": 1,
  "title": "Senior Java Developer Position",
  "status": "PARSED",
  "createdAt": "2024-01-15T11:00:00Z"
}
```

### Get JD by ID
**Endpoint:** `GET /jobs/{id}`

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "Senior Java Developer Position",
  "jobTitle": "Senior Java Developer",
  "requiredExperienceYears": 5,
  "domain": "Software Development",
  "requiredSkills": [
    {
      "name": "Java",
      "mandatory": true,
      "weight": 10
    },
    {
      "name": "Spring Boot",
      "mandatory": true,
      "weight": 8
    }
  ],
  "preferredSkills": [
    {
      "name": "Kubernetes",
      "mandatory": false,
      "weight": 5
    }
  ],
  "educationRequirements": ["Bachelor's degree in Computer Science"],
  "certificationRequirements": ["AWS Certification preferred"],
  "responsibilities": [
    "Design and develop scalable microservices",
    "Mentor junior developers"
  ],
  "softSkills": ["Communication", "Leadership", "Problem-solving"]
}
```

### List All JDs
**Endpoint:** `GET /jobs`  
**Query Params:** `page`, `size`, `sort`

**Response:** `200 OK`
```json
{
  "content": [...],
  "pageable": {...},
  "totalElements": 15,
  "totalPages": 2
}
```

### Update JD
**Endpoint:** `PUT /jobs/{id}`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "title": "Updated Job Title",
  "requiredExperienceYears": 7,
  "domain": "Cloud Computing"
}
```

**Response:** `200 OK`

### Delete JD
**Endpoint:** `DELETE /jobs/{id}`

**Response:** `204 No Content`

---

## 3. Evaluation Controller

### Create Evaluation
**Endpoint:** `POST /evaluations`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "resumeId": 1,
  "jobDescriptionId": 1
}
```

**Response:** `200 OK`
```json
{
  "evaluationId": 1,
  "resumeId": 1,
  "jobDescriptionId": 1,
  "status": "PROCESSING",
  "message": "Evaluation started. Check status using GET /evaluations/{id}"
}
```

**Async Response (when completed):**
```json
{
  "id": 1,
  "resumeId": 1,
  "jobDescriptionId": 1,
  "overallMatchPercentage": 82.5,
  "recommendation": "Strong Match",
  "highLevelSummary": "Candidate demonstrates strong alignment with the job requirements. Extensive experience in Java and Spring Boot matches core technical needs.",
  "technicalSkillScore": 85.0,
  "experienceScore": 80.0,
  "domainScore": 75.0,
  "educationScore": 90.0,
  "certificationScore": 60.0,
  "achievementScore": 78.0,
  "softSkillScore": 70.0,
  "matchedSkills": [
    {
      "resumeSkillName": "Java",
      "jobSkillName": "Java",
      "matchType": "Exact",
      "similarityScore": 1.0,
      "explanation": "Exact match for core programming language"
    },
    {
      "resumeSkillName": "Spring Framework",
      "jobSkillName": "Spring Boot",
      "matchType": "Semantic",
      "similarityScore": 0.92,
      "explanation": "Spring Framework experience directly applicable to Spring Boot"
    }
  ],
  "missingSkills": [
    {
      "jobSkillName": "Kubernetes",
      "explanation": "No Kubernetes experience found in resume"
    }
  ],
  "partialMatchedSkills": [
    {
      "resumeSkillName": "Docker",
      "jobSkillName": "Kubernetes",
      "matchType": "Partial",
      "similarityScore": 0.65,
      "explanation": "Container orchestration knowledge present but not at Kubernetes level"
    }
  ],
  "skillDistributionPieChart": [
    { "name": "Backend", "value": 45 },
    { "name": "Cloud", "value": 25 },
    { "name": "Database", "value": 15 },
    { "name": "DevOps", "value": 15 }
  ],
  "skillTimelineBarChart": [
    { "skill": "Java", "years": 8 },
    { "skill": "Spring Boot", "years": 6 },
    { "skill": "Microservices", "years": 5 },
    { "skill": "AWS", "years": 4 }
  ],
  "degrees": [
    {
      "degree": "Bachelor of Science in Computer Science",
      "university": "MIT",
      "year": "2015"
    }
  ],
  "certifications": [
    {
      "name": "AWS Certified Solutions Architect",
      "issuer": "Amazon Web Services",
      "year": "2022"
    }
  ],
  "achievements": [
    {
      "title": "Led migration to microservices",
      "description": "Migrated monolithic application",
      "impact": "Reduced deployment time by 60%"
    }
  ],
  "strengths": [
    "Strong Java and Spring Boot expertise",
    "Proven track record in microservices architecture",
    "Relevant AWS certification"
  ],
  "improvements": [
    "Gain hands-on Kubernetes experience",
    "Consider obtaining CKAD certification",
    "Expand cloud-native development skills"
  ],
  "evaluatedAt": "2024-01-15T12:00:00Z"
}
```

### Get Evaluation Result
**Endpoint:** `GET /evaluations/{id}`

**Response:** `200 OK` (Same structure as above)

### Get Evaluation History
**Endpoint:** `GET /evaluations/history`  
**Query Params:** `page`, `size`, `resumeId`, `jobId`, `status`

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "resumeId": 1,
      "candidateName": "John Doe",
      "jobDescriptionId": 1,
      "jobTitle": "Senior Java Developer",
      "overallMatchPercentage": 82.5,
      "recommendation": "Strong Match",
      "status": "COMPLETED",
      "evaluatedAt": "2024-01-15T12:00:00Z"
    }
  ],
  "pageable": {...},
  "totalElements": 50
}
```

### Compare Candidates
**Endpoint:** `POST /evaluations/compare`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "jobDescriptionId": 1,
  "resumeIds": [1, 2, 3]
}
```

**Response:** `200 OK`
```json
{
  "jobDescriptionId": 1,
  "jobTitle": "Senior Java Developer",
  "comparisons": [
    {
      "resumeId": 1,
      "candidateName": "John Doe",
      "overallMatchPercentage": 82.5,
      "recommendation": "Strong Match",
      "rank": 1
    },
    {
      "resumeId": 2,
      "candidateName": "Jane Smith",
      "overallMatchPercentage": 78.0,
      "recommendation": "Moderate Match",
      "rank": 2
    }
  ]
}
```

### Delete Evaluation
**Endpoint:** `DELETE /evaluations/{id}`

**Response:** `204 No Content`

---

## 4. Report Controller

### Download PDF Report
**Endpoint:** `GET /reports/{evaluationId}/download`

**Response:** `200 OK`
```
Content-Type: application/pdf
Content-Disposition: attachment; filename="evaluation_report_1.pdf"

[binary PDF content]
```

### Generate Custom Report
**Endpoint:** `POST /reports/generate`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "evaluationIds": [1, 2, 3],
  "includeCharts": true,
  "includeDetailedAnalysis": true,
  "format": "PDF"
}
```

**Response:** `200 OK` (PDF binary)

---

## 5. Health & Info Endpoints

### Health Check
**Endpoint:** `GET /actuator/health`

**Response:** `200 OK`
```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "ai": { "status": "UP" }
  }
}
```

### API Info
**Endpoint:** `GET /actuator/info`

**Response:** `200 OK`
```json
{
  "app": {
    "name": "Resume AI Evaluation System",
    "version": "1.0.0"
  }
}
```

---

## Error Responses

### Standard Error Format
```json
{
  "timestamp": "2024-01-15T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid file format. Supported formats: PDF, DOC, DOCX",
  "path": "/api/v1/resumes/upload",
  "details": []
}
```

### Common HTTP Status Codes
- `200 OK`: Successful request
- `201 Created`: Resource created successfully
- `204 No Content`: Successful deletion
- `400 Bad Request`: Invalid input
- `401 Unauthorized`: Missing or invalid authentication
- `403 Forbidden`: Insufficient permissions
- `404 Not Found`: Resource not found
- `409 Conflict`: Resource already exists
- `422 Unprocessable Entity`: AI parsing failed
- `500 Internal Server Error`: Server error
- `503 Service Unavailable`: AI service unavailable

---

## Rate Limiting
- Default: 100 requests per minute per user
- File uploads: 10 requests per minute per user
- Evaluations: 5 requests per minute per user

Rate limit headers included in responses:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1642248600
```
