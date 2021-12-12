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
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value=rating
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ms-4">
          {{ rating }}
        </div>
      </v-row>

      <div class="my-4 text-subtitle-1">
        {{ autor }}
      </div>

      <div>{{ description }}</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>{{ subtitle }}</v-card-title>

    <v-card-text>
      <v-chip-group
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>
        <v-chip>7:30PM</v-chip>
        <v-chip>8:00PM</v-chip>
        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
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
  props: ['title', 'subtitle', 'description', 'autor', 'rating', 'imageUrl']
}
</script>
