# Builds ldap-mock image. Called by: docker-compose.yml service ldap-mock
FROM python:3.11-slim
WORKDIR /app
COPY server.py .
EXPOSE 8089
CMD ["python", "-u", "server.py"]
