<template>
  <v-row align="end">
    <v-col class="text-center" v-for="book in books" :key="book.id">
      <Card :title=book.name :imageUrl=book.imageUrl :description=book.description :subtitle = book.subtitle />
    </v-col>
  </v-row>
</template>


<script>
export default {
  data () {
    return {
      books: []
    }
  },
  mounted () {
    this.books = this.fetchBooks();
    console.log(this.books)
  },
  methods: {
    async fetchBooks() {
      const books = await this.$axios.$get('book/')
      this.books = books
    }
  }
}
</script>
