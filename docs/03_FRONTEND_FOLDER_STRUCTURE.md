# Frontend Folder Structure

```
frontend/
├── angular.json
├── package.json
├── tsconfig.json
├── proxy.conf.json
├── src/
│   ├── app/
│   │   ├── app.component.ts
│   │   ├── app.component.html
│   │   ├── app.component.scss
│   │   ├── app.module.ts
│   │   ├── app-routing.module.ts
│   │   ├── core/
│   │   │   ├── core.module.ts
│   │   │   ├── guards/
│   │   │   │   └── auth.guard.ts
│   │   │   ├── interceptors/
│   │   │   │   ├── http-error.interceptor.ts
│   │   │   │   └── auth.interceptor.ts
│   │   │   └── services/
│   │   │       ├── resume.service.ts
│   │   │       ├── job-description.service.ts
│   │   │       ├── evaluation.service.ts
│   │   │       ├── report.service.ts
│   │   │       └── notification.service.ts
│   │   ├── features/
│   │   │   ├── dashboard/
│   │   │   │   ├── dashboard.component.ts
│   │   │   │   ├── dashboard.component.html
│   │   │   │   ├── dashboard.component.scss
│   │   │   │   └── dashboard.module.ts
│   │   │   ├── resume-upload/
│   │   │   │   ├── resume-upload.component.ts
│   │   │   │   ├── resume-upload.component.html
│   │   │   │   ├── resume-upload.component.scss
│   │   │   │   └── resume-upload.module.ts
│   │   │   ├── jd-upload/
│   │   │   │   ├── jd-upload.component.ts
│   │   │   │   ├── jd-upload.component.html
│   │   │   │   ├── jd-upload.component.scss
│   │   │   │   └── jd-upload.module.ts
│   │   │   ├── evaluation-result/
│   │   │   │   ├── evaluation-result.component.ts
│   │   │   │   ├── evaluation-result.component.html
│   │   │   │   ├── evaluation-result.component.scss
│   │   │   │   └── evaluation-result.module.ts
│   │   │   ├── candidate-comparison/
│   │   │   │   ├── candidate-comparison.component.ts
│   │   │   │   ├── candidate-comparison.component.html
│   │   │   │   ├── candidate-comparison.component.scss
│   │   │   │   └── candidate-comparison.module.ts
│   │   │   ├── match-history/
│   │   │   │   ├── match-history.component.ts
│   │   │   │   ├── match-history.component.html
│   │   │   │   ├── match-history.component.scss
│   │   │   │   └── match-history.module.ts
│   │   │   └── report-download/
│   │   │       ├── report-download.component.ts
│   │   │       ├── report-download.component.html
│   │   │       ├── report-download.component.scss
│   │   │       └── report-download.module.ts
│   │   └── shared/
│   │       ├── shared.module.ts
│   │       ├── components/
│   │       │   ├── header/
│   │       │   │   ├── header.component.ts
│   │       │   │   ├── header.component.html
│   │       │   │   └── header.component.scss
│   │       │   ├── sidebar/
│   │       │   │   ├── sidebar.component.ts
│   │       │   │   ├── sidebar.component.html
│   │       │   │   └── sidebar.component.scss
│   │       │   ├── file-upload/
│   │       │   │   ├── file-upload.component.ts
│   │       │   │   ├── file-upload.component.html
│   │       │   │   └── file-upload.component.scss
│   │       │   ├── skill-chart/
│   │       │   │   ├── skill-chart.component.ts
│   │       │   │   ├── skill-chart.component.html
│   │       │   │   └── skill-chart.component.scss
│   │       │   ├── match-score-card/
│   │       │   │   ├── match-score-card.component.ts
│   │       │   │   ├── match-score-card.component.html
│   │       │   │   └── match-score-card.component.scss
│   │       │   └── loading-spinner/
│   │       │       ├── loading-spinner.component.ts
│   │       │       ├── loading-spinner.component.html
│   │       │       └── loading-spinner.component.scss
│   │       ├── models/
│   │       │   ├── resume.models.ts
│   │       │   ├── job-description.models.ts
│   │       │   ├── evaluation.models.ts
│   │       │   ├── chart.models.ts
│   │       │   └── api-response.models.ts
│   │       ├── pipes/
│   │       │   ├── match-percentage.pipe.ts
│   │       │   ├── date-format.pipe.ts
│   │       │   └── skill-category.pipe.ts
│   │       └── utils/
│   │           ├── validators.ts
│   │           └── constants.ts
│   ├── assets/
│   │   ├── images/
│   │   ├── icons/
│   │   └── i18n/
│   ├── environments/
│   │   ├── environment.ts
│   │   └── environment.prod.ts
│   ├── styles/
│   │   ├── _variables.scss
│   │   ├── _mixins.scss
│   │   └── styles.scss
│   ├── index.html
│   ├── main.ts
│   └── polyfills.ts
├── Dockerfile
└── nginx.conf
```

## Module Descriptions

### core
- **guards**: Route protection (auth guard)
- **interceptors**: HTTP request/response manipulation
- **services**: Singleton services for API calls, notifications

### features
Feature modules are lazy-loaded and contain:
- Smart components (container components with logic)
- Feature-specific routing
- Local state management

**Key Components:**
- `dashboard`: Overview stats, recent evaluations
- `resume-upload`: File upload, text input, parsing status
- `jd-upload`: JD creation/upload interface
- `evaluation-result`: Full evaluation display with charts
- `candidate-comparison`: Side-by-side candidate comparison
- `match-history`: Historical evaluations list
- `report-download`: PDF report generation/download

### shared
- **components**: Reusable dumb components (presentational)
  - `header`: Top navigation bar
  - `sidebar`: Side navigation menu
  - `file-upload`: Drag-drop file uploader
  - `skill-chart`: ECharts wrapper for visualizations
  - `match-score-card`: Score display card
  - `loading-spinner`: Loading indicator

- **models**: TypeScript interfaces for API responses
- **pipes**: Custom data transformation pipes
- **utils**: Validation functions, constants

## Component Architecture

### Smart Components (Container)
- Fetch data from services
- Manage component state
- Handle user interactions
- Pass data to dumb components

### Dumb Components (Presentational)
- Receive data via @Input
- Emit events via @Output
- No direct service dependencies
- Highly reusable

## State Management
- Services with RxJS BehaviorSubject for simple state
- NgRx can be added for complex state if needed

## Chart Integration
- Apache ECharts wrapped in Angular components
- Reactive data binding
- Responsive chart resizing
