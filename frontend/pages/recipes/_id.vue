<template>
  <v-container>
    <v-card :color="color" class="mb-8" elevation="10">
      <v-row no-gutters>
        <v-col cols="12" lg="6"
               align-self="center">
          <v-card
            class="my-auto"
            min-height="300px"
          >
            <v-card-text>
              <div>{{recipe.bookName}}</div>
              <p class="text-h4 text--primary">
                {{recipe.name}}

              </p>
              <p>Publicznie dostępna: <b>{{recipe.published ? "Tak" : "Nie"}}</b></p>
              <div class="text--primary">
                {{recipe.description}}
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="deep-purple accent-4"
                @click="$router.push({name: 'books-id', params: { id: '' }})"
              >
                Wróć do książki
              </v-btn>
            </v-card-actions>
            <v-card-actions>
              <v-row no-gutters class="d-flex justify-end">
                <v-col cols="3">
                  <Dialog btnLabel="Usuń"
                          title="Czy chcesz usunąć tą książkę?"
                          description="Spowoduje to usunięcie wszystkich zapisanych w niej przepisów."
                          color="error"
                          :actionUrl="'recipe/'+id"
                          actionType="delete"
                          route="_reload"
                  />
                </v-col>
                <v-col cols="3">
                  <v-btn
                    text
                    color="primary"
                    @click="$router.push({name: 'recipes-update-id', params:{ id: id}})"
                  >
                    Aktualizuj
                  </v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="12" lg="6">
          <v-img max-height='350px' :src="recipe.imageUrl"/>
        </v-col>
      </v-row>
    </v-card>
    <v-card elevation="10" :color="color">
      <v-row class="px-2">
          <v-col cols="12" lg="4">
            <v-card color="#EFEBE9" class="my-3" min-height="80px">
              <v-row class="mx-6 font-weight-medium d-flex justify-space-between" >
                <v-col cols="4">
                  Czas przygotowania:
                </v-col>
                <v-col cols="2" align-self="center">
                  1h
                  <v-icon size="30">mdi-alarm</v-icon>
                </v-col>
                <v-col cols="5" align-self="center">
                  Koszt:
                  <v-icon size="30">mdi-currency-usd</v-icon>
                  <v-icon size="30">mdi-currency-usd</v-icon>
                  <v-icon size="30">mdi-currency-usd</v-icon>
                </v-col>
              </v-row>

            </v-card>
            <v-card
              class="mx-auto"
              tile
            >
              <v-card-text>
                <p class="text-h5 text--primary">
                  Składniki
                </p>
              </v-card-text>
              <div  v-for="(ingredient, index) in recipe.ingredients">
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title>{{index+1}}. {{ingredient.name}} {{ingredient.amount+ingredient.unit}}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-divider></v-divider>
              </div>
            </v-card>
          </v-col>
          <v-col cols="12" lg="8">
            <v-card
              class="mx-auto"
              tile
            >
              <v-card-text>
                <p class="text-h5 text--primary">
                  Przygotowanie krok po kroku
                </p>
              </v-card-text>
              <div  v-for="(step, index) in recipe.steps">
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title>Krok {{index+1}}</v-list-item-title>
                    <v-list-item-subtitle>{{step}}</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
                <v-divider></v-divider>
              </div>
            </v-card>
          </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data(){
    return {
      id: null,
      recipe: {},
      color: "#D7CCC8",
    }
  },
  created() {
    this.id = this.$route.params.id;
    this.fetchRecipe(this.id)
  },
  mounted() {
  },
  methods: {
    async fetchRecipe(id) {
      try {
        const recipe = await this.$axios.$get(`recipe/${id}`)
        this.recipe = recipe
        this.recipe.steps = recipe.steps.split('<br>');
      } catch (err) {
        if (err.response.status === 404) {
          return this.$nuxt.error({statusCode: 404, message: err.message})
        }
      }
    },
  }
}
</script>

<style scoped>

</style>
