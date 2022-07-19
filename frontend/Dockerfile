FROM node:16-alpine AS build
WORKDIR /usr/local/src/app
COPY ./ /usr/local/src/app/
RUN npm install
RUN npm run build --configuration=production

FROM nginx:1.23.0-alpine
COPY --from=build /usr/local/src/app/dist/frontend /usr/share/nginx/html
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80