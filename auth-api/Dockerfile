FROM node:16
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 9191
CMD ["node", "app.js"]