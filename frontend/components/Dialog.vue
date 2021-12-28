<template>
  <v-row justify="center">

    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :color="color || 'primary'"
          text
          v-bind="attrs"
          v-on="on"
          class="mt-3"
        >
          {{ btnLabel }}
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5">
          {{ title }}
        </v-card-title>
        <v-card-text>{{ description }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="red darken-1"
            text
            @click="dialog = false"
          >
            Anuluj
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="performAction(actionUrl, actionType)"
          >
            Potwierdź
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "Dialog",
  data () {
    return {
      dialog: false,
      loading: false,
    }
  },
  props: ['actionUrl','actionType','route', 'title', 'description', 'btnLabel', 'color'],
  methods:{
    async performAction(actionUrl, actionType){
      if(actionType == 'delete'){
        this.loading = true
        const res = await this.$axios.$delete(actionUrl)
        this.loading = false
        console.log('Deleted')
        console.log(res)
        this.dialog = false
        console.log(this.route)
        if(this.route == "_reload"){
          this.$router.go(0)
        }
        else if(this.route){
          this.$router.push({name: this.route})
        }

      }
      else{
        console.log("Unknown action type!", actionType)
      }
    }
  }
}
</script>

<style scoped>

</style>
