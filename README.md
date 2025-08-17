# 🌾 **AgroServe DevOps Platform**

![AgroServe Logo](https://raw.githubusercontent.com/Sumitapex761/AgroServe-Enterprise-DevOps/main/assets/logo.png)

> A **Full-Stack Agriculture Services Platform** with **DevOps pipeline, monitoring, and security**, fully deployable **locally** or on **Kubernetes multi-node clusters**.

---

## 🚀 **Project Overview**

AgroServe is designed to manage agricultural service bookings, providers, users, and notifications.  
It integrates **backend (Spring Boot)**, **frontend (React)**, **MySQL**, and a **complete DevOps toolchain**.

**Highlights:**
- 💻 Full-stack application
- ⚙️ Complete **CI/CD pipeline**
- 🛡 Security & code quality with **SonarQube, OWASP, Trivy**
- 📊 Monitoring with **Prometheus & Grafana**
- 🐳 Containerized deployment with **Docker & Kubernetes**

---

## 🛠 **Tech Stack & DevOps Tools**

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

---

## 🏗 **Local Setup**

### **1️⃣ Backend (Spring Boot)**
```bash
cd backend/
mvn clean install
mvn spring-boot:run
