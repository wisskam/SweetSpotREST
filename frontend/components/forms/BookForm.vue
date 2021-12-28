<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-overlay :value="loading">
      <v-progress-circular
        indeterminate
        size="64"
      ></v-progress-circular>
    </v-overlay>
    <v-text-field
      v-model="book.name"
      :counter="30"
      :rules="nameRules"
      label="Nazwa"
      required
    ></v-text-field>
    <v-text-field
      v-model="book.description"
      label="Opis"
    ></v-text-field>
    <v-text-field
      v-model="book.imageUrl"
      label="Zdjęcie"
      required
    ></v-text-field>

    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="performAction"
    >
      Zapisz
    </v-btn>
    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Wyczyść formularz
    </v-btn>
    <v-btn
      color="primary"
      class="mr-4"
      @click="$router.back()"
    >
      Anuluj
    </v-btn>

  </v-form>
</template>

<script>
export default {
  name: "BookForm",
  data(){
    return {
      valid: false,
      loading: true,
      id: null,
      book: {},
      recipes: [],
      nameRules: [
        v => !!v || 'Nazwa jest wymagana',
        v => (v && v.length <= 30) || 'Nazwa musi być krótsza niż 30 znaków',
      ],
    }
  },
  props: ['bookId'],
  mounted() {
    this.valid = false;
    this.id = this.$props.bookId
    if(this.id){
      this.fetchBook(this.id)
    }
    this.loading = false;
  },
  methods: {
    async fetchBook(id) {
      const book = await this.$axios.$get(`book/${id}`)
      this.book = book
    },
    async performAction(){
      if(this.id){
        await this.update()
      }
      else{
        await this.add()
      }

    },
    async add(){
      try{
        this.validate();
        if(this.valid){
          const newBook = await this.$axios.$post('book/', this.book)
          await this.$router.push({ name: "books-id", params: { id: newBook.id }})
        }
      }
      catch(err){
        console.log(err)
      }
    },
    async update(){
      try{
        this.validate();
        if(this.valid){
          const res = this.$axios.$put('book/'+this.id, this.book)
          await this.$router.push({ name: "books-id" , params: { id: this.id }})
        }
      }
      catch(err){
        console.log(err)
      }
    },
    validate () {
      this.$refs.form.validate()
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  }
}
</script>

<style scoped>

</style>
