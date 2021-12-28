<template>
  <BookForm :bookId="id"/>
</template>

<script>
import BookForm from "@/components/forms/BookForm";
export default {
  components: {BookForm},
  data(){
    return{
      id: null,
    }
  },
  created(){
    this.id = this.$route.params.id
  }
}
</script>

<style scoped>

</style>
