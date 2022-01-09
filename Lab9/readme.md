# MicroProfile [Lab8](https://profs.info.uaic.ro/~acf/tj/labs/lab_09.html) & [Lab9](https://profs.info.uaic.ro/~acf/tj/labs/lab_10.html)

### Tasks Done:
## 1. **LAB 9**
- Expose a CRUD REST resource from the previous lab as a microservice.
  - Run the microservice using an Eclipse Microprofile server implementation - **Open Liberty**;
  - Created an additional microservice that will invoke the first one; 
  - Created **Docker** containers for the microservices. **Database** also deployed as a container.

## 2. **LAB 10**
- Implemented simple test cases to highlight the support offered by MicroProfile for writing **resilient** microservices.
  - **Fallback + Timeout and Retry**; 
  - **CircuitBreaker**;
  - **Bulkhead thread-pool**;
  - **Semaphore**.
- Implemented and tested a **health check procedure**, in order to determine the **readiness** and the **liveness** of the service.
- Used **MicroProfile Metrics API** in order to monitor the behaviour of the service. Analyzed the number of invocations and the response time for at least one method.