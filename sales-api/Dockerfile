FROM node:16
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 9192
CMD ["node", "app.js"]