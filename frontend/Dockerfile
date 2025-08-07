FROM node:18-alpine AS build
WORKDIR /app
COPY . .
RUN npm ci
RUN npm run build --configuration=production

FROM nginx:stable-alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80