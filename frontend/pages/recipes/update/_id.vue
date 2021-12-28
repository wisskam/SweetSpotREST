<template>
  <v-container>
    <RecipeForm :bookId="bookId" :recipeId="recipeId"/>
  </v-container>
</template>

<script>
import RecipeForm from "@/components/forms/RecipeForm";
export default {
  components: {RecipeForm},
  data(){
    return{
      bookId: null,
      recipeId: null,
    }
  },
  created() {
    this.bookId = this.$route.query.bookId;
    this.recipeId = this.$route.params.id;
  }
}
</script>

<style scoped>

</style>
