# Offizielles Node-Image verwenden
FROM node:22-alpine

# Arbeitsverzeichnis im Container
WORKDIR /app

# package.json und package-lock.json kopieren
COPY package*.json ./

# Dependencies installieren
RUN npm install

# Restlichen Code kopieren
COPY . .

# Port für Render (Render setzt PORT automatisch)
ENV PORT=8080
EXPOSE 8080

# Server starten
CMD ["npm", "start"]
