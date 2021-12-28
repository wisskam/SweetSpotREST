<template>
  <v-container>
    <v-fab-transition >
      <v-btn
        color="orange"
        dark
        absolute
        right
        fab
        fixed
        to="books/add"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-fab-transition>
    <v-row align="end">
      <v-col class="text-center" v-for="book in books" :key="book.id">
        <Card :title=book.name :imageUrl=book.imageUrl :description=book.description :subtitle = book.subtitle :id="book.id"/>
      </v-col>
    </v-row>
  </v-container>
</template>


<script>
import Card from "@/components/Card";
export default {
  components: {Card},
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
