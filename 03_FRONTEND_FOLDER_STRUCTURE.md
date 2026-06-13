# Frontend Folder Structure

```
frontend/
├── angular.json                                    # Angular workspace configuration
├── package.json                                    # Node.js dependencies
├── tsconfig.json                                   # TypeScript configuration
├── proxy.conf.json                                 # API proxy for development
├── Dockerfile                                      # Container image definition
├── src/
│   ├── app/
│   │   ├── app.component.ts                        # Root component
│   │   ├── app.component.html                      # Root template
│   │   ├── app.component.scss                      # Root styles
│   │   ├── app.config.ts                           # Application configuration
│   │   ├── app.routes.ts                           # Main routing configuration
│   │   ├── core/
│   │   │   ├── core.module.ts                      # Core module (singleton services)
│   │   │   ├── interceptors/
│   │   │   │   ├── http-error.interceptor.ts       # Global error handling
│   │   │   │   ├── auth.interceptor.ts             # JWT token injection
│   │   │   │   └── loading.interceptor.ts          # Loading indicator
│   │   │   ├── guards/
│   │   │   │   └── auth.guard.ts                   # Route protection
│   │   │   ├── resolvers/
│   │   │   │   └── evaluation.resolver.ts          # Pre-fetch evaluation data
│   │   │   ├── services/
│   │   │   │   ├── auth.service.ts                 # Authentication service
│   │   │   │   ├── resume.service.ts               # Resume API calls
│   │   │   │   ├── job-description.service.ts      # JD API calls
│   │   │   │   ├── evaluation.service.ts           # Evaluation API calls
│   │   │   │   ├── report.service.ts               # Report download service
│   │   │   │   ├── notification.service.ts         # Toast notifications
│   │   │   │   └── loading.service.ts              # Loading spinner service
│   │   │   └── models/
│   │   │       ├── resume.model.ts                 # Resume interfaces
│   │   │       ├── job-description.model.ts        # JD interfaces
│   │   │       ├── evaluation.model.ts             # Evaluation interfaces
│   │   │       ├── skill.model.ts                  # Skill interfaces
│   │   │       └── api-response.model.ts           # API response wrappers
│   │   ├── shared/
│   │   │   ├── shared.module.ts                    # Shared module
│   │   │   ├── components/
│   │   │   │   ├── header/
│   │   │   │   │   ├── header.component.ts
│   │   │   │   │   └── header.component.html
│   │   │   │   ├── sidebar/
│   │   │   │   │   ├── sidebar.component.ts
│   │   │   │   │   └── sidebar.component.html
│   │   │   │   ├── footer/
│   │   │   │   │   ├── footer.component.ts
│   │   │   │   │   └── footer.component.html
│   │   │   ├── directives/
│   │   │   │   └── file-validator.directive.ts     # File upload validation
│   │   │   ├── pipes/
│   │   │   │   ├── score-percentage.pipe.ts        # Format score as percentage
│   │   │   │   ├── date-format.pipe.ts             # Custom date formatting
│   │   │   │   └── safe-html.pipe.ts               # Sanitize HTML
│   │   │   └── utils/
│   │   │       ├── validators.ts                   # Form validators
│   │   │       ├── constants.ts                    # App constants
│   │   │       └── helpers.ts                      # Utility functions
│   │   └── features/
│   │       ├── dashboard/
│   │       │   ├── dashboard.component.ts
│   │       │   ├── dashboard.component.html
│   │       │   ├── dashboard.component.scss
│   │       │   ├── dashboard.routes.ts
│   │       │   └── components/
│   │       │       ├── stats-cards/
│   │       │       │   ├── stats-cards.component.ts
│   │       │       │   └── stats-cards.component.html
│   │       │       └── recent-evaluations/
│   │       │           ├── recent-evaluations.component.ts
│   │       │           └── recent-evaluations.component.html
│   │       ├── resume-upload/
│   │       │   ├── resume-upload.component.ts
│   │       │   ├── resume-upload.component.html
│   │       │   ├── resume-upload.component.scss
│   │       │   ├── resume-upload.routes.ts
│   │       │   └── components/
│   │       │       ├── file-drop-zone/
│   │       │       │   ├── file-drop-zone.component.ts
│   │       │       │   └── file-drop-zone.component.html
│   │       │       ├── upload-progress/
│   │       │       │   ├── upload-progress.component.ts
│   │       │       │   └── upload-progress.component.html
│   │       │       └── resume-preview/
│   │       │           ├── resume-preview.component.ts
│   │       │           └── resume-preview.component.html
│   │       ├── jd-upload/
│   │       │   ├── jd-upload.component.ts
│   │       │   ├── jd-upload.component.html
│   │       │   ├── jd-upload.component.scss
│   │       │   ├── jd-upload.routes.ts
│   │       │   └── components/
│   │       │       ├── jd-text-editor/
│   │       │       │   ├── jd-text-editor.component.ts
│   │       │       │   └── jd-text-editor.component.html
│   │       │       └── jd-file-upload/
│   │       │           ├── jd-file-upload.component.ts
│   │       │           └── jd-file-upload.component.html
│   │       ├── evaluation-result/
│   │       │   ├── evaluation-result.component.ts
│   │       │   ├── evaluation-result.component.html
│   │       │   ├── evaluation-result.component.scss
│   │       │   ├── evaluation-result.routes.ts
│   │       │   └── components/
│   │       │       ├── summary-header/
│   │       │       │   ├── summary-header.component.ts
│   │       │       │   └── summary-header.component.html
│   │       │       ├── score-breakdown/
│   │       │       │   ├── score-breakdown.component.ts
│   │       │       │   └── score-breakdown.component.html
│   │       │       ├── skill-pie-chart/
│   │       │       │   ├── skill-pie-chart.component.ts
│   │       │       │   └── skill-pie-chart.component.html
│   │       │       ├── skill-timeline-bar/
│   │       │       │   ├── skill-timeline-bar.component.ts
│   │       │       │   └── skill-timeline-bar.component.html
│   │       │       ├── matched-skills-table/
│   │       │       │   ├── matched-skills-table.component.ts
│   │       │       │   └── matched-skills-table.component.html
│   │       │       ├── missing-skills-table/
│   │       │       │   ├── missing-skills-table.component.ts
│   │       │       │   └── missing-skills-table.component.html
│   │       │       ├── degrees-section/
│   │       │       │   ├── degrees-section.component.ts
│   │       │       │   └── degrees-section.component.html
│   │       │       ├── certifications-section/
│   │       │       │   ├── certifications-section.component.ts
│   │       │       │   └── certifications-section.component.html
│   │       │       ├── achievements-section/
│   │       │       │   ├── achievements-section.component.ts
│   │       │       │   └── achievements-section.component.html
│   │       │       └── strengths-improvements/
│   │       │           ├── strengths-improvements.component.ts
│   │       │           └── strengths-improvements.component.html
│   │       ├── candidate-comparison/
│   │       │   ├── candidate-comparison.component.ts
│   │       │   ├── candidate-comparison.component.html
│   │       │   ├── candidate-comparison.component.scss
│   │       │   ├── candidate-comparison.routes.ts
│   │       │   └── components/
│   │       │       ├── comparison-table/
│   │       │       │   ├── comparison-table.component.ts
│   │       │       │   └── comparison-table.component.html
│   │       │       └── radar-chart/
│   │       │           ├── radar-chart.component.ts
│   │       │           └── radar-chart.component.html
│   │       ├── match-history/
│   │       │   ├── match-history.component.ts
│   │       │   ├── match-history.component.html
│   │       │   ├── match-history.component.scss
│   │       │   ├── match-history.routes.ts
│   │       │   └── components/
│   │       │       ├── history-filters/
│   │       │       │   ├── history-filters.component.ts
│   │       │       │   └── history-filters.component.html
│   │       │       └── history-table/
│   │       │           ├── history-table.component.ts
│   │       │           └── history-table.component.html
│   │       └── report-download/
│   │           ├── report-download.component.ts
│   │           ├── report-download.component.html
│   │           └── report-download.component.scss
│   ├── assets/
│   │   ├── images/
│   │   │   ├── logo.png
│   │   │   ├── resume-icon.svg
│   │   │   └── jd-icon.svg
│   │   ├── icons/
│   │   │   ├── match-strong.svg
│   │   │   ├── match-moderate.svg
│   │   │   ├── match-weak.svg
│   │   │   └── match-reject.svg
│   │   └── i18n/
│   │       └── en.json                             # Internationalization
│   ├── environments/
│   │   ├── environment.ts                          # Development environment
│   │   └── environment.prod.ts                     # Production environment
│   ├── styles/
│   │   ├── _variables.scss                         # SCSS variables
│   │   ├── _mixins.scss                            # SCSS mixins
│   │   ├── _theme.scss                             # Angular Material theme
│   │   └── styles.scss                             # Global styles
│   └── index.html                                  # Main HTML file
├── .eslintrc.json                                  # ESLint configuration
├── .prettierrc                                     # Code formatting
├── jest.config.js                                  # Unit testing config
├── cypress.config.ts                               # E2E testing config
└── README.md
```

## Key Configuration Files

### package.json (Key Dependencies)
```json
{
  "dependencies": {
    "@angular/core": "^17.0.0",
    "@angular/material": "^17.0.0",
    "@angular/cdk": "^17.0.0",
    "echarts": "^5.4.0",
    "ngx-echarts": "^12.0.0",
    "rxjs": "^7.8.0",
    "@angular/router": "^17.0.0",
    "@angular/forms": "^17.0.0",
    "@angular/common/http": "^17.0.0"
  },
  "devDependencies": {
    "typescript": "^5.2.0",
    "jest": "^29.7.0",
    "cypress": "^13.0.0",
    "eslint": "^8.50.0",
    "prettier": "^3.0.0"
  }
}
```

### app.routes.ts (Main Routing)
```typescript
const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadChildren: () => import('./features/dashboard/dashboard.routes')
      .then(m => m.DASHBOARD_ROUTES)
  },
  {
    path: 'resume-upload',
    loadChildren: () => import('./features/resume-upload/resume-upload.routes')
      .then(m => m.RESUME_UPLOAD_ROUTES)
  },
  {
    path: 'jd-upload',
    loadChildren: () => import('./features/jd-upload/jd-upload.routes')
      .then(m => m.JD_UPLOAD_ROUTES)
  },
  {
    path: 'evaluation/:id',
    loadChildren: () => import('./features/evaluation-result/evaluation-result.routes')
      .then(m => m.EVALUATION_RESULT_ROUTES),
    resolve: { evaluationData: EvaluationResolver }
  },
  {
    path: 'candidate-comparison',
    loadChildren: () => import('./features/candidate-comparison/candidate-comparison.routes')
      .then(m => m.CANDIDATE_COMPARISON_ROUTES)
  },
  {
    path: 'match-history',
    loadChildren: () => import('./features/match-history/match-history.routes')
      .then(m => m.MATCH_HISTORY_ROUTES)
  },
  {
    path: 'report/:evaluationId',
    loadChildren: () => import('./features/report-download/report-download.routes')
      .then(m => m.REPORT_DOWNLOAD_ROUTES)
  },
  { path: '**', redirectTo: 'dashboard' }
];
```

### proxy.conf.json (API Proxy for Dev)
```json
{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "logLevel": "debug"
  }
}
```

## Component Architecture

### Smart vs Dumb Components
- **Smart Components** (Containers): Handle data fetching, state management
  - evaluation-result.component.ts
  - dashboard.component.ts
  - match-history.component.ts

- **Dumb Components** (Presentational): Receive data via @Input(), emit events via @Output()
  - skill-pie-chart.component.ts
  - matched-skills-table.component.ts
  - score-breakdown.component.ts

### State Management Strategy
- Use RxJS BehaviorSubject for simple state
- Consider NgRx/Signals for complex state if needed
- Services maintain component state across navigation

### Chart Integration (Apache ECharts)
```typescript
// Example chart option structure
pieChartOption = {
  title: { text: 'Skill Distribution', left: 'center' },
  tooltip: { trigger: 'item' },
  series: [{
    type: 'pie',
    radius: '50%',
    data: chartData,
    emphasis: {
      itemStyle: {
        shadowBlur: 10,
        shadowOffsetX: 0,
        shadowColor: 'rgba(0, 0, 0, 0.5)'
      }
    }
  }]
};
```
