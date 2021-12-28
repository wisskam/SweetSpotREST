<template>
  <v-container>
    <v-parallax
      src="https://bratek.waw.pl/wp-content/uploads/2014/07/photodune-6761938-food-background-on-dark-slate-m-1.jpg"
    >
      <v-row
        align="center"
        justify="center"
      >
        <v-col
          class="text-center"
          cols="12"
        >
          <h1 class="text-h2 font-weight-thin mb-4">
            Sweet Spot - Przepisy
          </h1>
          <h4 class="text-h5 font-weight-medium">
            Zorganizuj swoje pyszności!
          </h4>
        </v-col>
      </v-row>
    </v-parallax>
    <v-row>
    <v-card
      v-for="recipe in publicRecipes"
      class="mx-auto my-12"
      max-width="374"
    >
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-img
        height="250"
        :src="recipe.imageUrl"
      ></v-img>

      <v-card-title>{{ recipe.name }}</v-card-title>

      <v-card-text>
        <v-row
          align="center"
          class="mx-0"
        >
          <v-rating
            :value="4.5"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ms-4">
            4.5 (413)
          </div>
        </v-row>

        <div class="my-4 text-subtitle-1">
          {{ recipe.bookAuthorFirstName + ' ' + recipe.bookAuthorLastName }}
        </div>

        <div>{{ recipe.description }}</div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-actions class="d-flex justify-center">
        <v-btn
          color="orange lighten-1"
          dark
          @click="$router.push({name: 'recipes-id', params: { id: recipe.id }})"
        >
          Zobacz przepis
        </v-btn>
      </v-card-actions>
    </v-card>
    </v-row>
  </v-container>

</template>
<script>
export default {
  auth: false,
  data(){
    return{
      publicRecipes: [],
    }
  },
  mounted() {
    this.fetchPublicRecipes();
  },
  methods: {
    async fetchPublicRecipes(limit=10){
      const recipes = await this.$axios.get('recipe/public');
      this.publicRecipes = recipes.data.sort();

    }
  }

}
</script>
