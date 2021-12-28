<template>
  <v-container>
    <v-card class="pa-2" color="#FFAD10">
      <v-row no-gutters>
        <v-col
          cols="12"
          sm="4"
        >
          <v-card-title>{{ book.name || '' }}</v-card-title>
          <v-card-subtitle>{{ (book.userFirstName || '') + ' ' + (book.userLastName || '') }}</v-card-subtitle>
          <v-card-text>{{ book.description || '' }}</v-card-text>

          <v-card-title>Liczba przepisów: {{ recipes.length }}</v-card-title>

        </v-col>
        <v-col
          cols="12"
          sm="2"
        >
        </v-col>
        <v-col
          cols="12"
          sm="6"
        >
          <v-img :src="book.imageUrl" max-height="200px"/>
        </v-col>
        <v-col cols="12" class="mt-2 orange lighten-3 d-flex justify-center">
          <v-row justify="end">
            <v-col cols="3">
              <Dialog btnLabel="Usuń"
                      title="Czy chcesz usunąć tą książkę?"
                      description="Spowoduje to usunięcie wszystkich zapisanych w niej przepisów."
                      color="error"
                      :actionUrl="'book/'+book.id"
                      actionType="delete"
                      route="books"
              />
            </v-col>
            <v-col cols="3">
              <v-btn
                text
                color="primary"
                :to="'update/'+(id)"
      >
        Aktualizuj
      </v-btn>
      </v-col>
      </v-row>

      </v-col>
      </v-row>
      </v-card>

      <v-fab-transition >
        <v-btn
          color="orange"
          style="margin-bottom: 100px; margin-right: 50px;"
          dark
          absolute
          right
          bottom
          fab
          fixed
          @click="$router.push({name: 'recipes-add', query:{ bookId: id}})"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-fab-transition>

    <RecipeList :recipes="recipes" class="mt-15"/>
  </v-container>
</template>


<script>
import Dialog from "@/components/Dialog";
import RecipeList from "@/components/RecipeList";

export default {
  data() {
    return {
      id: null,
      book: {},
      recipes: [],
    }
  },
  created() {
    this.id = this.$route.params.id;
    this.fetchBook(this.id)
  },
  mounted() {
    this.fetchBookRecipes(this.id)
  },
  methods: {
    async fetchBook(id) {
      try {
        const book = await this.$axios.$get(`book/${id}`)
        this.book = book
      } catch (err) {
        if (err.response.status === 404) {
          return this.$nuxt.error({statusCode: 404, message: err.message})
        }
      }
    },
    async fetchBookRecipes(id) {
      const recipes = await this.$axios.$get(`book/${id}/recipes`)
      this.recipes = recipes.sort((a,b) => (a.modifiedAt > b.modifiedAt) ? -1 : ((b.modifiedAt > a.modifiedAt) ? 1 : 0))
    }
  }
}
</script>

<style scoped>

</style>
