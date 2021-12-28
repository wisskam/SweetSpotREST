<template>
<v-container>
  <RecipeForm :bookId="bookId"/>
</v-container>
</template>

<script>
import RecipeForm from "@/components/forms/RecipeForm";
export default {
  components: {RecipeForm},
  data(){
    return{
      bookId: null,
    }
  },
  created() {
    this.bookId = this.$route.query.bookId;
  }
}
</script>

<style scoped>

</style>
