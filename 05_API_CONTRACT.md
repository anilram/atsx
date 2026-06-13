# API Contract Specification

## Base URL
```
Development: http://localhost:8080/api/v1
Production: https://api.yourdomain.com/api/v1
```

## Authentication
All endpoints (except health checks) require JWT Bearer token authentication.

```
Authorization: Bearer <jwt_token>
```

---

## 1. Resume Management APIs

### 1.1 Upload Resume File
**Endpoint:** `POST /resumes/upload`  
**Content-Type:** `multipart/form-data`

**Request:**
```
file: <binary> (PDF, DOC, DOCX - max 10MB)
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "resumeId": 1,
    "fileName": "john_doe_resume.pdf",
    "fileSize": 524288,
    "mimeType": "application/pdf",
    "status": "PROCESSING",
    "message": "Resume uploaded successfully. Processing..."
  }
}
```

**Error Responses:**
- `400 Bad Request`: Invalid file type or size exceeded
- `401 Unauthorized`: Missing or invalid token
- `500 Internal Server Error`: Upload failed

---

### 1.2 Submit Resume as Text
**Endpoint:** `POST /resumes/text`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "text": "John Doe\nSenior Java Developer\nExperience: 8 years...\n[Full resume text]"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "resumeId": 1,
    "fileName": "text-input.txt",
    "status": "PROCESSING",
    "message": "Resume text submitted. Processing..."
  }
}
```

---

### 1.3 Get Resume Details
**Endpoint:** `GET /resumes/{resumeId}`

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "candidateName": "John Doe",
    "email": "john.doe@email.com",
    "phone": "+1-555-0123",
    "totalExperienceYears": 8.5,
    "currentRole": "Senior Java Developer",
    "summary": "Experienced Java developer with expertise in Spring Boot...",
    "skills": [
      {
        "name": "Java",
        "category": "Backend",
        "years": 8.0,
        "level": "Expert"
      },
      {
        "name": "Spring Boot",
        "category": "Backend",
        "years": 6.0,
        "level": "Advanced"
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
        "description": "Migrated monolithic application to microservices architecture",
        "impact": "Reduced deployment time by 60%"
      }
    ],
    "parsingStatus": "COMPLETED",
    "createdAt": "2024-01-15T10:30:00Z"
  }
}
```

---

### 1.4 List All Resumes
**Endpoint:** `GET /resumes`  
**Query Parameters:**
- `page` (optional, default: 0): Page number
- `size` (optional, default: 10): Items per page
- `status` (optional): Filter by parsing status

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": 1,
        "candidateName": "John Doe",
        "currentRole": "Senior Java Developer",
        "totalExperienceYears": 8.5,
        "parsingStatus": "COMPLETED",
        "createdAt": "2024-01-15T10:30:00Z"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "totalPages": 5,
      "totalElements": 50
    }
  }
}
```

---

### 1.5 Delete Resume
**Endpoint:** `DELETE /resumes/{resumeId}`

**Response (204 No Content):**
```
(Empty body)
```

---

## 2. Job Description Management APIs

### 2.1 Upload JD File
**Endpoint:** `POST /jobs/upload`  
**Content-Type:** `multipart/form-data`

**Request:**
```
file: <binary> (PDF, DOC, DOCX - max 10MB)
title: "Senior Java Developer" (optional)
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "jobDescriptionId": 1,
    "fileName": "jd_senior_java.pdf",
    "title": "Senior Java Developer",
    "status": "PROCESSING",
    "message": "Job description uploaded successfully. Processing..."
  }
}
```

---

### 2.2 Submit JD as Text
**Endpoint:** `POST /jobs/text`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "title": "Senior Java Developer",
  "text": "We are looking for a Senior Java Developer with 5+ years of experience...\n[Full JD text]"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "jobDescriptionId": 1,
    "title": "Senior Java Developer",
    "status": "PROCESSING",
    "message": "Job description submitted. Processing..."
  }
}
```

---

### 2.3 Get JD Details
**Endpoint:** `GET /jobs/{jobDescriptionId}`

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "Senior Java Developer",
    "jobTitle": "Senior Java Developer",
    "requiredExperienceYears": 5.0,
    "domain": "Software Development",
    "requiredSkills": [
      {
        "name": "Java",
        "mandatory": true,
        "weight": 5
      },
      {
        "name": "Spring Boot",
        "mandatory": true,
        "weight": 5
      },
      {
        "name": "Microservices",
        "mandatory": false,
        "weight": 3
      }
    ],
    "preferredSkills": [
      {
        "name": "Kubernetes",
        "mandatory": false,
        "weight": 2
      }
    ],
    "educationRequirements": [
      "Bachelor's degree in Computer Science or related field"
    ],
    "certificationRequirements": [
      "AWS Certification (preferred)"
    ],
    "responsibilities": [
      "Design and develop scalable microservices",
      "Lead technical discussions",
      "Mentor junior developers"
    ],
    "softSkills": [
      "Communication",
      "Leadership",
      "Problem-solving"
    ],
    "parsingStatus": "COMPLETED",
    "createdAt": "2024-01-15T09:00:00Z"
  }
}
```

---

### 2.4 List All Job Descriptions
**Endpoint:** `GET /jobs`  
**Query Parameters:**
- `page`, `size`, `status` (same as resumes)

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": 1,
        "title": "Senior Java Developer",
        "jobTitle": "Senior Java Developer",
        "requiredExperienceYears": 5.0,
        "domain": "Software Development",
        "parsingStatus": "COMPLETED",
        "createdAt": "2024-01-15T09:00:00Z"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "totalPages": 3,
      "totalElements": 25
    }
  }
}
```

---

## 3. Evaluation APIs

### 3.1 Create Evaluation
**Endpoint:** `POST /evaluations`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "resumeId": 1,
  "jobDescriptionId": 1
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "evaluationId": 1,
    "resumeId": 1,
    "jobDescriptionId": 1,
    "status": "COMPLETED",
    "message": "Evaluation completed successfully"
  }
}
```

**Processing Flow:**
1. Backend fetches resume and JD profiles
2. Runs skill matching algorithms
3. Calculates scores using hybrid scoring engine
4. Generates chart data
5. Persists results
6. Returns full evaluation result

---

### 3.2 Get Evaluation Result
**Endpoint:** `GET /evaluations/{evaluationId}`

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "resumeId": 1,
    "jobDescriptionId": 1,
    "candidateName": "John Doe",
    "jobTitle": "Senior Java Developer",
    "overallMatchPercentage": 82.5,
    "recommendation": "Strong Match",
    "highLevelSummary": "John is an excellent match for this position with strong technical skills in Java and Spring Boot, exceeding the required experience level. His background in microservices architecture aligns well with the job requirements.",
    
    "technicalSkillScore": 85.0,
    "experienceScore": 90.0,
    "domainScore": 75.0,
    "educationScore": 90.0,
    "certificationScore": 60.0,
    "achievementScore": 78.0,
    "softSkillScore": 70.0,
    
    "matchedSkills": [
      {
        "skillName": "Java",
        "resumeYears": 8.0,
        "requiredYears": 5.0,
        "matchType": "EXACT",
        "similarityScore": 1.0
      },
      {
        "skillName": "Spring Boot",
        "resumeYears": 6.0,
        "requiredYears": 4.0,
        "matchType": "EXACT",
        "similarityScore": 1.0
      }
    ],
    
    "missingSkills": [
      {
        "skillName": "Kubernetes",
        "matchType": "MISSING",
        "importance": "PREFERRED"
      }
    ],
    
    "partialMatchedSkills": [
      {
        "skillName": "Cloud Platforms",
        "matchedWith": "AWS",
        "matchType": "SEMANTIC",
        "similarityScore": 0.85
      }
    ],
    
    "skillDistributionPieChart": [
      { "category": "Backend", "value": 60, "color": "#4CAF50" },
      { "category": "Cloud", "value": 20, "color": "#2196F3" },
      { "category": "Database", "value": 15, "color": "#FF9800" },
      { "category": "DevOps", "value": 5, "color": "#9C27B0" }
    ],
    
    "skillTimelineBarChart": [
      { "skill": "Java", "years": 8.0, "level": "Expert" },
      { "skill": "Spring Boot", "years": 6.0, "level": "Advanced" },
      { "skill": "Microservices", "years": 4.0, "level": "Advanced" },
      { "skill": "AWS", "years": 3.0, "level": "Intermediate" }
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
        "description": "Migrated monolithic application to microservices architecture",
        "impact": "Reduced deployment time by 60%"
      }
    ],
    
    "strengths": [
      "Extensive Java development experience (8 years vs 5 required)",
      "Strong background in microservices architecture",
      "Proven leadership in technical projects",
      "Relevant AWS certification"
    ],
    
    "improvements": [
      "Consider gaining Kubernetes certification",
      "Could benefit from more experience with CI/CD pipelines",
      "Additional cloud platform knowledge (Azure/GCP) would be valuable"
    ],
    
    "createdAt": "2024-01-15T11:00:00Z",
    "updatedAt": "2024-01-15T11:00:00Z"
  }
}
```

---

### 3.3 Get Evaluation History
**Endpoint:** `GET /evaluations/history`  
**Query Parameters:**
- `page`, `size` (pagination)
- `resumeId` (optional): Filter by resume
- `jobDescriptionId` (optional): Filter by JD
- `recommendation` (optional): Filter by recommendation type
- `sortBy` (optional): Field to sort by (default: createdAt)
- `sortOrder` (optional): asc or desc (default: desc)

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": 1,
        "candidateName": "John Doe",
        "jobTitle": "Senior Java Developer",
        "overallMatchPercentage": 82.5,
        "recommendation": "Strong Match",
        "createdAt": "2024-01-15T11:00:00Z"
      },
      {
        "id": 2,
        "candidateName": "Jane Smith",
        "jobTitle": "Senior Java Developer",
        "overallMatchPercentage": 65.0,
        "recommendation": "Moderate Match",
        "createdAt": "2024-01-14T15:30:00Z"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "totalPages": 15,
      "totalElements": 150
    }
  }
}
```

---

### 3.4 Compare Multiple Candidates
**Endpoint:** `POST /evaluations/compare`  
**Content-Type:** `application/json`

**Request:**
```json
{
  "jobDescriptionId": 1,
  "resumeIds": [1, 2, 3]
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "jobDescriptionId": 1,
    "jobTitle": "Senior Java Developer",
    "comparisons": [
      {
        "resumeId": 1,
        "candidateName": "John Doe",
        "overallMatchPercentage": 82.5,
        "recommendation": "Strong Match",
        "technicalSkillScore": 85.0,
        "experienceScore": 90.0,
        "rank": 1
      },
      {
        "resumeId": 2,
        "candidateName": "Jane Smith",
        "overallMatchPercentage": 65.0,
        "recommendation": "Moderate Match",
        "technicalSkillScore": 70.0,
        "experienceScore": 60.0,
        "rank": 2
      }
    ],
    "radarChartData": {
      "labels": ["Technical Skills", "Experience", "Domain", "Education", "Certifications", "Achievements", "Soft Skills"],
      "datasets": [
        {
          "label": "John Doe",
          "data": [85, 90, 75, 90, 60, 78, 70]
        },
        {
          "label": "Jane Smith",
          "data": [70, 60, 80, 85, 75, 65, 75]
        }
      ]
    }
  }
}
```

---

## 4. Report Generation APIs

### 4.1 Download PDF Report
**Endpoint:** `GET /reports/{evaluationId}/download`  
**Produces:** `application/pdf`

**Response (200 OK):**
```
Content-Type: application/pdf
Content-Disposition: attachment; filename="evaluation_report_1.pdf"

<binary PDF content>
```

---

### 4.2 Preview Report (HTML)
**Endpoint:** `GET /reports/{evaluationId}/preview`  
**Produces:** `text/html`

**Response (200 OK):**
```html
<!DOCTYPE html>
<html>
<head>
  <title>Evaluation Report - John Doe</title>
  <style>...</style>
</head>
<body>
  <!-- HTML report preview -->
</body>
</html>
```

---

## 5. Health & Utility APIs

### 5.1 Health Check
**Endpoint:** `GET /health`  
**Authentication:** Not required

**Response (200 OK):**
```json
{
  "status": "UP",
  "components": {
    "database": { "status": "UP" },
    "aiService": { "status": "UP" },
    "diskSpace": { "status": "UP", "details": { "total": 107374182400, "free": 53687091200 } }
  }
}
```

---

### 5.2 AI Service Status
**Endpoint:** `GET /health/ai`  
**Authentication:** Required

**Response (200 OK):**
```json
{
  "provider": "Anthropic Claude",
  "model": "claude-3-5-sonnet-20240620",
  "status": "OPERATIONAL",
  "responseTimeMs": 1250,
  "requestsRemaining": 9850
}
```

---

## Error Response Format

All error responses follow this standard format:

```json
{
  "success": false,
  "error": {
    "code": "ERROR_CODE",
    "message": "Human-readable error message",
    "details": "Additional context (optional)",
    "timestamp": "2024-01-15T11:00:00Z",
    "path": "/api/v1/resumes/upload"
  }
}
```

### Common Error Codes

| Code | HTTP Status | Description |
|------|-------------|-------------|
| `VALIDATION_ERROR` | 400 | Request validation failed |
| `FILE_TYPE_NOT_SUPPORTED` | 400 | Uploaded file type not allowed |
| `FILE_SIZE_EXCEEDED` | 400 | File exceeds maximum size |
| `UNAUTHORIZED` | 401 | Missing or invalid authentication |
| `FORBIDDEN` | 403 | Insufficient permissions |
| `RESOURCE_NOT_FOUND` | 404 | Requested resource doesn't exist |
| `DUPLICATE_RESOURCE` | 409 | Resource already exists |
| `AI_SERVICE_ERROR` | 502 | AI provider service error |
| `INTERNAL_SERVER_ERROR` | 500 | Unexpected server error |

---

## Rate Limiting

| Endpoint Category | Rate Limit | Window |
|-------------------|------------|--------|
| File Upload | 10 requests | per minute |
| Text Submission | 20 requests | per minute |
| Evaluation Creation | 5 requests | per minute |
| Report Generation | 10 requests | per minute |
| Read Operations | 100 requests | per minute |

Rate limit headers included in responses:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1705320000
```

---

## Versioning

API version is included in the path: `/api/v1/...`

Future versions will follow: `/api/v2/...`, etc.

Deprecated endpoints will be marked with:
```
Deprecation: true
Sunset: Sat, 01 Jun 2025 00:00:00 GMT
```
