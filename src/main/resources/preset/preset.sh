#!/bin/bash
export LANG=C.UTF-8
export LC_ALL=C.UTF-8

# Config
BASE_URL="http://localhost:8080"
CONTENT_TYPE="Content-Type: application/json; charset=UTF-8"

# Terminal colors
GREEN='\033[0;32m'
NC='\033[0m' # No Color

echo -e "${GREEN}Preset Requests started${NC}"

echo -e "${GREEN}Adding:${NC}"

#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Bruschetta al Pomodoro${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Bruschetta al Pomodoro.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Carpaccio di Manzo${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Carpaccio di Manzo.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Antipasto Mediterraneo${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Antipasto Mediterraneo.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Tagliatelle al Tartufo${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Tagliatelle al Tartufo.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Pollo alla Cacciatora${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Pollo alla Cacciatora.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Branzino al Forno${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Branzino al Forno.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Risotto ai Frutti di Mare${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Risotto ai Frutti di Mare.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Melanzane alla Parmigiana${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Melanzane alla Parmigiana.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Margherita Classica${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Margherita Classica.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Quattro Stagioni${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Quattro Stagioni.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Diavola Piccante${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Diavola Piccante.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Pizza Prosciutto e Rucola${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Pizza Prosciutto e Rucola.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Pizza Bianca al Gorgonzola${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Pizza Bianca al Gorgonzola.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Insalata Caprese${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Insalata Caprese.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Insalata Mediterranea${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Insalata Mediterranea.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Insalata con Gamberi${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Insalata con Gamberi.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Tiramisu della Casa${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Tiramisu della Casa.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Panna Cotta alla Vaniglia${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Panna Cotta alla Vaniglia.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Cannoli Siciliani${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Cannoli Siciliani.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Acqua Naturale${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Acqua Naturale.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Acqua Frizzante${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Acqua Frizzante.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Coca-Cola / Zero${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Acqua Frizzante.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Succo d'Arancia${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Coca-ColaZero.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Limonata Italiana${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Limonata Italiana.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Caffè Espresso${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Caffe Espresso.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Prosecco Frizzante${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Prosecco Frizzante.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Aperol Spritz${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Aperol Spritz.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Vino Rosso della Casa${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Vino Rosso della Casa.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Negroni Classico${NC}"

curl -X POST "$BASE_URL/menu/dish/add" \
     -H "$CONTENT_TYPE" \
     -d @'payload/Negroni Classico.json'
#---------------------------------------------------------------------------------------------------------------------------------------
echo -e "\n${GREEN}Done!${NC}"
