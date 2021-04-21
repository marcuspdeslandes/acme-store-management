<template>
  <div v-if="currentStore" class="edit-form">
    <h4>Store</h4>
    <form>
      <div class="form-group">
        <label for="id">Id</label>
        <input disabled="true"
          type="text"
          class="form-control"
          id="id"
          v-model="currentStore.id"
        />
      </div>
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          v-model="currentStore.name"
        />
      </div>

    </form>

    <b-button variant="outline-success" @click="updateStore">
      Update
    </b-button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Store...</p>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import StoreDataService from "../services/StoreDataService";

@Component
export default class Store extends Vue {
  private currentStore: any = null;
  private message: string = "";

  getStore(id: string, name: string) {
    this.currentStore = {
      id: id,
      name: name
    }
  }

  updateStore() {
    StoreDataService.update(this.currentStore.id, this.currentStore.name)
      .then((response) => {
        console.log(response.data);
        this.message = "The store was updated successfully!";
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.message = "";
    this.getStore(this.$route.params.id, this.$route.params.name);
  }
}
</script>

<style scoped>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
