# 🌾 AgroServe: Enterprise-Grade Agriculture Services Platform

[![Build Status](https://img.shields.io/jenkins/build?label=Jenkins&color=orange)](https://your-jenkins-url)
[![Docker](https://img.shields.io/docker/pulls/sumitapex761/agroserve-backend)](https://hub.docker.com/r/sumitapex761/agroserve-backend)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Cluster-blue)](https://kubernetes.io/)
[![SonarQube](https://img.shields.io/sonar/quality_gate?server=https%3A%2F%2Fsonarqube.yourserver.com&project=AgroServe)](https://sonarqube.yourserver.com/dashboard?id=AgroServe)
[![Trivy](https://img.shields.io/badge/Security-Trivy-red)](https://aquasec.com/trivy)
[![OWASP](https://img.shields.io/badge/Security-OWASP-brightgreen)](https://owasp.org/)

**AgroServe** is a **full-stack agriculture services platform** with integrated **DevOps automation**, **monitoring**, and **security**.  
It manages **users, service providers, bookings, and notifications** with modern CI/CD pipelines and container orchestration.

---

## 🚀 Live Demo
> Frontend NodePort URL: `http://<any-node-ip>:32000`  
*(Replace `<any-node-ip>` with your Kubernetes node IP)*

---

## 🔥 Tech Stack & DevOps Tools

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![React](https://img.shields.io/badge/React-17.0-blue)
![Node.js](https://img.shields.io/badge/Node.js-18.0-green)
![Docker](https://img.shields.io/badge/Docker-Container-blue)
![Kubernetes](https://img.shields.io/badge/K8s-Cluster-blue)
![Prometheus](https://img.shields.io/badge/Prometheus-Monitor-orange)
![Grafana](https://img.shields.io/badge/Grafana-Dashboard-red)

- **CI/CD:** Git + Jenkins  
- **Containerization:** Docker  
- **Orchestration:** Kubernetes (kubeadm, Argo CD)  
- **Monitoring:** Prometheus, Grafana  
- **Security & Code Quality:** Trivy, OWASP Dependency Check, SonarQube  

---

## 🖥 Backend Overview

- Spring Boot backend for managing users, providers, services, bookings, notifications  
- Entities: `AgroUser`, `AgroServiceProvider`, `AgroService`, `AgroBooking`, `AgroNotification`  
- Validation: Jakarta Validation (`@NotBlank`, `@Email`, `@Pattern`)  
- Exception Handling: Global `@ControllerAdvice` for API errors  
- Database: MySQL 8, with JPA/Hibernate relationships  

---

## 🌐 Frontend Overview

- React.js app for managing agricultural services  
- NodePort service exposed at port `32000`  
- Communicates with backend via REST APIs  

---

## ⚙️ DevOps Highlights

### 1️⃣ CI/CD Pipeline
- Automated Jenkins pipeline:
  - Build backend & frontend
  - Run tests & SonarQube analysis
  - Docker build & push
  - Security scans (Trivy & OWASP)
  - Kubernetes deployment via Argo CD  

### 2️⃣ Containerization & Orchestration
- Dockerized backend & frontend  
- Kubernetes (namespace `agro`) for deployments, services, and secrets  
- PersistentVolumeClaims for MySQL storage  

### 3️⃣ Monitoring & Metrics
- Prometheus for metrics  
- Grafana dashboards for visual monitoring  

### 4️⃣ Security & Code Quality
- Trivy: Docker image vulnerability scans  
- OWASP Dependency Check: Dependency vulnerabilities  
- SonarQube: Continuous code quality & security checks  

---

## ☸️ Kubernetes Deployment Steps

```bash
kubectl create namespace agro

kubectl apply -f k8s-project/mysql-pvc.yaml -n agro
kubectl apply -f k8s-project/mysql-deployment.yaml -n agro
kubectl apply -f k8s-project/backend-deployment.yaml -n agro
kubectl apply -f k8s-project/frontend-deployment.yaml -n agro

kubectl get pods -n agro -o wide
kubectl get svc -n agro
