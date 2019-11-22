FROM node:lts-alpine AS builder
WORKDIR /home/node
RUN npm install -g npm
COPY package*.json ./
RUN npm install
COPY . .
RUN ./node_modules/.bin/ng build --optimization --output-path target

FROM nginx:alpine
EXPOSE 80
COPY --from=builder /home/node/target/ /usr/share/nginx/html
