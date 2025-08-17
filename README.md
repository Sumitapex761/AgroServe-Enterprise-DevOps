# 🌾 DevOps AgroServe Platform

![AgroServe Logo](https://raw.githubusercontent.com/Sumitapex761/AgroServe-Enterprise-DevOps/main/assets/logo.png)

[![CI/CD](https://img.shields.io/badge/CI/CD-Jenkins-orange?logo=jenkins)](https://jenkins.io/) 
[![Docker](https://img.shields.io/badge/Docker-Container-blue?logo=docker)](https://www.docker.com/) 
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Cluster-blue?logo=kubernetes)](https://kubernetes.io/) 
[![Prometheus](https://img.shields.io/badge/Monitoring-Prometheus-orange?logo=prometheus)](https://prometheus.io/) 
[![Grafana](https://img.shields.io/badge/Dashboard-Grafana-red?logo=grafana)](https://grafana.com/) 
[![SonarQube](https://img.shields.io/badge/Code_Quality-SonarQube-blue?logo=sonarqube)](https://www.sonarqube.org/)
[![Trivy](https://img.shields.io/badge/Security-Trivy-red)](https://aquasec.com/trivy)

---

## 🔥 DevOps-Centric Overview

**DevOps AgroServe** is a full-stack agriculture service platform **built to demonstrate modern DevOps practices**, including:

- **CI/CD pipelines** with automated build, test, code quality scan, and deployment  
- **Containerization** of backend and frontend using Docker  
- **Orchestration** with Kubernetes (`kubeadm`) and GitOps via Argo CD  
- **Security Scanning** using Trivy & OWASP dependency checks  
- **Monitoring** using Prometheus and Grafana dashboards  

This project **shows end-to-end DevOps workflow**, from code commit to automated deployment and live monitoring.

---

## 🛠 Tech & DevOps Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Java 17, Spring Boot 3.x, MySQL, JPA/Hibernate |
| **Frontend** | React.js |
| **CI/CD** | Jenkins Pipeline + SonarQube + Trivy + OWASP |
| **Containerization** | Docker Images for frontend & backend |
| **Orchestration** | Kubernetes (Namespace: `agro`) + Argo CD |
| **Monitoring** | Prometheus + Grafana |
| **Secrets & Storage** | Kubernetes Secrets, PVC for MySQL |

---

## ⚡ Local Setup (Frontend + Backend)

### 1️⃣ Backend
```bash
cd backend
mvn spring-boot:run
Runs on port 9090

Swagger UI: http://localhost:9090/swagger-ui.html


2️⃣ Frontend
cd frontend
npm install
npm start

Runs on port 3000
Communicates with backend at http://localhost:9

3️⃣ Access
Open frontend: http://localhost:3000
Full-stack app is running locally.

☸️ Kubernetes Deployment (Namespace agro)

kubectl create namespace agro

# MySQL PVC & Deployment
kubectl apply -f k8s-project/mysql-pvc.yaml -n agro
kubectl apply -f k8s-project/mysql-deployment.yaml -n agro

# Backend Deployment
kubectl apply -f k8s-project/backend-deployment.yaml -n agro

# Frontend Deployment
kubectl apply -f k8s-project/frontend-deployment.yaml -n agro

# Verify pods & services
kubectl get pods -n agro -o wide
kubectl get svc -n agro


Frontend NodePort: http://<any-node-ip>:32000

Backend ClusterIP: http://backend-service:9090 (internal cluster)



🚀 DevOps Pipeline Highlights

Git → Jenkins

Automated pipeline triggered on git push

Builds backend & frontend Docker images

Code Quality & Security

SonarQube scans Java backend code

OWASP dependency check for vulnerabilities

Trivy scans Docker images for security issues

Containerization

Docker images pushed to registry

Kubernetes deployments automated via Jenkins & Argo CD

Kubernetes & GitOps

Namespace: agro

Cluster-deployed MySQL, backend, and frontend services

Config & secrets managed via Kubernetes

Monitoring & Alerts

Prometheus collects metrics from pods

Grafana dashboards visualize CPU, memory, and app health

🏗 Project Features (AgroServe)

User CRUD, Service Provider CRUD, Bookings, Notifications

DTO-based request validation

Global Exception Handling

JWT Security (planned)

Swagger API documentation

📝 License

Distributed under MIT License. See LICENSE for details.


---

✅ **This version makes your GitHub repo look super DevOps-oriented**:

- DevOps tools appear at the top as badges  
- CI/CD, monitoring, containerization, GitOps are clearly highlighted  
- Local + Kubernetes deployment instructions are present  
- Professional formatting with emojis and tables  

---

If you want, I can **also make a final ultra-attractive “colored & illustrated README” with logos, arrows, and visual workflow diagram** — so when recruiters open your repo, it screams DevOps expertise.  

Do you want me to do that next?






