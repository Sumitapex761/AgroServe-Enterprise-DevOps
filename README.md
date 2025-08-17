# 🌾 DevOps AgroServe Platform

[![CI/CD](https://img.shields.io/badge/CI/CD-Jenkins-orange?logo=jenkins)](https://jenkins.io/) 
[![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker)](https://www.docker.com/) 
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Cluster-blue?logo=kubernetes)](https://kubernetes.io/) 
[![Prometheus](https://img.shields.io/badge/Monitoring-Prometheus-orange?logo=prometheus)](https://prometheus.io/) 
[![Grafana](https://img.shields.io/badge/Dashboard-Grafana-red?logo=grafana)](https://grafana.com/) 
[![SonarQube](https://img.shields.io/badge/Code_Quality-SonarQube-blue?logo=sonarqube)](https://www.sonarqube.org/)
[![Trivy](https://img.shields.io/badge/Security-Trivy-red)](https://aquasec.com/trivy)
[![OWASP](https://img.shields.io/badge/Security-OWASP-red)](https://owasp.org/)

---

## 🔥 Project Overview

**DevOps AgroServe** is a full-stack agricultural service platform demonstrating **modern DevOps practices**:

- Automated **CI/CD pipelines** for backend & frontend  
- **Containerization** using Docker  
- **Kubernetes orchestration** with Argo CD  
- **Code quality & security** via SonarQube, OWASP, Trivy  
- **Monitoring & dashboards** via Prometheus & Grafana  
- **Secrets & storage management** using Kubernetes Secrets & PVC  

This project showcases **end-to-end DevOps workflow** — from git commit to automated deployment and live monitoring.

---

## 🛠 Tech & DevOps Stack

| Tool | Purpose |
|------|---------|
| ![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white) | Version control |
| ![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white) | CI/CD automation |
| ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white) | Containerization |
| ![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white) | Multi-node deployment |
| ![ArgoCD](https://img.shields.io/badge/ArgoCD-EC1C24?style=for-the-badge&logo=argocd&logoColor=white) | GitOps deployment |
| ![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white) | Monitoring |
| ![Grafana](https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white) | Dashboard & Alerts |
| ![SonarQube](https://img.shields.io/badge/SonarQube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white) | Code quality & scanning |
| ![Trivy](https://img.shields.io/badge/Trivy-1A1A1A?style=for-the-badge&logoColor=white) | Container vulnerability scanning |
| ![OWASP](https://img.shields.io/badge/OWASP-FFFFFF?style=for-the-badge&logo=owasp&logoColor=orange) | Security best practices |
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) | Backend language |
| ![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) | Java framework |
| ![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black) | Frontend framework |
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) | Database |

---

## 🏗 Local Setup & Kubernetes Deployment

```bash
# Backend (Spring Boot)
cd backend/
mvn clean install
mvn spring-boot:run

# Backend URL & Swagger
# http://localhost:9090
# Swagger: http://localhost:9090/swagger-ui.html

# Frontend (React)
cd frontend/
npm install
npm start

# Frontend URL
# http://localhost:3000
# Ensure backend is running on port 9090

# Database (MySQL)
# Use Docker MySQL or local install
# DB Name: agrodb
# Credentials configurable via application.properties or Kubernetes Secret

# Kubernetes Deployment (Namespace: agro)
kubectl create namespace agro
kubectl apply -f k8s/mysql.yaml -n agro
kubectl apply -f k8s/backend.yaml -n agro
kubectl apply -f k8s/frontend.yaml -n agro
kubectl get pods -n agro
kubectl get svc -n agro

# Access URLs
# Frontend: http://<any-node-ip>:32000
# Backend: http://backend-service:9090
```

📌 **Features Implemented**

**Backend**
- User, Service Provider, Service, Booking & Notification management
- JWT authentication & role-based access
- Spring Data JPA + MySQL

**Frontend**
- ReactJS with Axios & Bootstrap
- Interactive booking dashboard
- API integration with backend

**Database**
- MySQL with PVC-enabled persistent storage

**Relationships**
- One-to-Many: Provider → Service
- Many-to-One: Booking → User/Service
- One-to-One: Notification → Booking

🎯 DevOps Highlights

CI/CD Pipeline: Jenkins builds, tests, and deploys automatically

Containerization: Docker images for backend & frontend

Orchestration: Kubernetes multi-node deployment

GitOps: ArgoCD automates deployments from Git repo

Monitoring: Prometheus collects metrics, Grafana visualizes dashboards

Security & Code Quality:

SonarQube for static code analysis

Trivy for container vulnerability scanning

OWASP best practices applied

🔧 **How to Contribute**
```bash
# Fork this repository
# Create a branch
git checkout -b feature-name

# Commit changes
git commit -m "Add feature"

# Push branch
git push origin feature-name

# Open a pull request
```
📜 License

MIT License. See LICENSE for details.


This is **fully DevOps-focused**, highlighting **pipelines, containers, orchestration, monitoring, and security**, with badges, tech stack, and Kubernetes deployment — ready to **showcase your DevOps skills** in GitHub portfolio.  

If you want, I can also make a **super “live badge” version** showing **Jenkins build status, Docker pulls, SonarQube quality, and Prometheus alerts** all in the README — looks ultra-professional.  

Do you want me to do that next?





✅ **Why this is better:**  
- Each major section (Backend, Frontend, Database, Relationships, DevOps highlights) has a clear heading  
- Subpoints use `-` for lists, making it visually clean  
- Code block for Git commands is properly closed with triple backticks and `bash`  
- Nothing overlaps or looks messy  

