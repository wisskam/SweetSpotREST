<template>
  <v-card
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
      :src=imageUrl
    ></v-img>

    <v-card-title>{{ title }}</v-card-title>

    <v-card-text>
<!--      <v-row-->
<!--        align="center"-->
<!--        class="mx-0"-->
<!--      >-->
<!--        <v-rating-->
<!--          :value=rating-->
<!--          color="amber"-->
<!--          dense-->
<!--          half-increments-->
<!--          readonly-->
<!--          size="14"-->
<!--        ></v-rating>-->

<!--        <div class="grey&#45;&#45;text ms-4">-->
<!--          {{ rating }}-->
<!--        </div>-->
<!--      </v-row>-->

      <div class="my-4 text-subtitle-1">
        {{ owner }}
      </div>

      <div>{{ description }}</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

<!--    <v-card-title>{{ subtitle }}</v-card-title>-->

    <v-card-actions >
      <v-row class="d-flex justify-center">
        <v-col cols="6">
          <Dialog btnLabel="Usuń"
                  title="Czy chcesz usunąć tą książkę?"
                  description="Spowoduje to usunięcie wszystkich zapisanych w niej przepisów."
                  color="error"
                  :actionUrl="'book/'+id"
                  actionType="delete"
                  route="_reload"
          />
        </v-col>
        <v-col cols="6">
          <v-btn
            text
            color="primary"
            :to="'books/update/'+(id)"
          >
            Aktualizuj
          </v-btn>
        </v-col>
      </v-row>
    </v-card-actions>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        :to="'books/'+id"
      >
        Zobacz wszystkie przepisy
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data(){
    return {
      selected: null
    }
  },
  props: ['title', 'subtitle', 'description', 'owner', 'rating', 'imageUrl', 'id'],
  methods: {
    booksIdLink(id){
      return "books/" + id;
    }

  }
}
</script>
