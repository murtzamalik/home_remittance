# CRA frontend + nginx. Callers: docker-compose service frontend
# User: deploy pure stack on local docker
FROM node:14-bullseye AS build
WORKDIR /app
COPY Home_Remittance_FrontEnd/package.json Home_Remittance_FrontEnd/package-lock.json ./
RUN npm ci --legacy-peer-deps || npm install --legacy-peer-deps
COPY Home_Remittance_FrontEnd/ ./
ENV REACT_APP_BASE_URL_LIVE=http://localhost
RUN npm run build

FROM nginx:1.27-alpine
COPY docker/gateway/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
