
# EVENTRA – Enterprise Incident Orchestration Platform

[![Build Status](https://img.shields.io/github/actions/workflow/status/Shubh00796/EVENTRA-ENTERPRICE_INCIDENT_ORCHESTRATION_PLATFORM/ci.yml?branch=main)](https://github.com/Shubh00796/EVENTRA-ENTERPRICE_INCIDENT_ORCHESTRATION_PLATFORM/actions)  
[![Code Coverage](https://img.shields.io/codecov/c/github/Shubh00796/EVENTRA-ENTERPRICE_INCIDENT_ORCHESTRATION_PLATFORM)](https://codecov.io/gh/Shubh00796/EVENTRA-ENTERPRICE_INCIDENT_ORCHESTRATION_PLATFORM)  
[![License](https://img.shields.io/github/license/Shubh00796/EVENTRA-ENTERPRICE_INCIDENT_ORCHESTRATION_PLATFORM)](LICENSE)

## Table of Contents
1. [Overview](#overview)  
2. [Architecture](#architecture)  
3. [Key Features](#key-features)  
4. [Tech Stack](#tech-stack)  
5. [Prerequisites](#prerequisites)  
6. [Getting Started](#getting-started)  
7. [Docker & Docker Compose](#docker--docker-compose)  
8. [API Reference](#api-reference)  
9. [Testing](#testing)  
10. [CI/CD](#ci-cd)  
11. [Deployment](#deployment)  
12. [Monitoring & Logging](#monitoring--logging)  
13. [Contributing](#contributing)  
14. [License](#license)  
15. [Contact](#contact)

---

## Overview
**EVENTRA** is a centralized incident orchestration engine designed to ingest alerts from multiple sources, enrich them, run custom workflows, and dispatch notifications or remediation actions across teams and tools.

Typical flow:
1. **Ingestion**: Receives incidents via HTTP webhooks or message queues.  
2. **Normalization**: Transforms raw payloads into a common `Incident` model.  
3. **Orchestration**: Executes defined workflows (e.g. auto-acknowledge, escalate, run remediation scripts).  
4. **Notification**: Sends alerts via email, Slack, or ticketing systems.  
5. **Audit & Reporting**: Persists all steps for traceability and analytics.

---

## Architecture

```mermaid
flowchart TD
  subgraph Ingestion
    A[Webhooks/API] -->|POST /incidents| API[API Gateway]
    B[Kafka Consumer] -->|topic: raw-incidents| Orch
  end

  subgraph Core
    API --> Orch[Orchestration Engine]
    Orch -->|Normalize| Store[(MongoDB)]
    Orch -->|Workflow Trigger| WF[Workflow Service]
    WF -->|Action: notify| Notif[Notification Service]
    WF -->|Action: script| Exec[Execution Service]
    Notif -->|Email/Slack| External[Teams & Tools]
  end

  subgraph Persistence
    Store -->|Audit Logs| ES[(Elasticsearch)]
    Store -->|Metrics| Prom[(Prometheus)]
  end