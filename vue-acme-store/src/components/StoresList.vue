<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by name"
          v-model="name"
        />
        <div class="input-group-append">
          <b-button
            type="button"
            @click="searchName"
          >
            Search
          </b-button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Store List</h4>
      <b-table striped hover :id="stores.id" onclick="setActiveStore(store, index)"></b-table>
      <ul class="list-group">
        <li
          class="list-group-item"
          :class="{ active: index == selectedIndex }"
          v-for="(store, index) in stores"
          :key="index"
          @click="setActiveStore(store, index)"
        >
          {{ store.id + ' - ' + store.name }}
        </li>
      </ul>

      <b-button class="m-3 btn btn-sm" @click="firstPage" :disabled="currentPage===0">
        First
      </b-button>

      <b-button class="m-3 btn btn-sm" @click="previousPage" :disabled="currentPage===0">
        Previous
      </b-button>

      <b-button class="m-3 btn btn-sm" @click="nextPage" :disabled="currentPage===totalElements-1">
        Next
      </b-button>

      <b-button class="m-3 btn btn-sm" @click="lastPage" :disabled="currentPage===totalElements-1">
        Last
      </b-button>

    </div>
    <div class="col-md-6">
      <div v-if="currentStore">
        <h4>Store {{ currentStore.id }}</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentStore.name }}
          <b-button variant="outline-secondary" class="m-3 btn btn-sm" :href="'/stores/' + currentStore.id + '/' + currentStore.name ">
            Edit
          </b-button>
        </div>
        <div>
          <label><strong>Code:</strong></label>
          {{ currentStore.code }}
        </div>
        <div>
          <label><strong>Store type:</strong></label>
          {{ currentStore.storeType}}
        </div>
        <div>
          <label><strong>Store season:</strong></label>
          {{ currentStore.storeSeason}}
        </div>
        <div>
          <label><strong>Opening date:</strong></label>
          {{ currentStore.openingDate | formatDate }}
        </div>
        <div>
          <label><strong>Description:</strong></label>
          {{ currentStore.description }}
        </div>
        <div>
          <label><strong>Special field 1:</strong></label>
          {{ currentStore.specialField1 }}
        </div>
        <div>
          <label><strong>Special field 2:</strong></label>
          {{ currentStore.specialField2 }}
        </div>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Store...</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import StoreDataService from "../services/StoreDataService";

@Component
export default class StoresList extends Vue {
  private name: string = "";

  private currentStore: any = null;
  private selectedIndex: number = -1;
  private selectedPage: number = 0;

  private currentPage: number = 0;
  private stores: any[] = [];
  private totalElements: number = 0;


  retrieveStores() {
    StoreDataService.getAll(this.currentPage)
      .then((response) => {
        this.stores = response.data.data.map(this.mapperStore);
        this.totalElements = response.data.totalElements;
        console.log(response.data.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  setActiveStore(store: any, index: number) {
    this.currentStore = store;
    this.selectedIndex = index;
    this.selectedPage = index;
  }

  firstPage() {
    this.currentPage = 0;
    this.selectedIndex = -1;
    this.retrieveStores();
  }

  lastPage() {
    this.currentPage = this.totalElements-1;
    this.selectedIndex = -1;
    this.retrieveStores();
  }

  previousPage() {
    this.currentPage--;
    this.selectedIndex = -1;
    this.retrieveStores();
  }

  nextPage() {
    this.currentPage++;
    this.selectedIndex = -1;
    this.retrieveStores();
  }

  searchName() {
    StoreDataService.findByName(this.name)
      .then((response) => {
        this.stores = response.data.data.map(this.mapperStore);
        console.log(response.data.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mapperStore (data: any) {
      return {
        id: data && data.id ? data.id : '',
        code: data && data.code ? data.code : '',
        description: data && data.description ? data.description : '',
        name: data && data.name ? data.name : '',
        openingDate: data && data.openingDate ? data.openingDate : '',
        storeType: data && data.storeType ? data.storeType : '',
        storeSeason: data && data.storeSeason ? data.storeSeason : '',
        specialField1: data && data.specialField1 ? data.specialField1 : '',
        specialField2: data && data.specialField2 ? data.specialField2 : ''
      };
    };

  mounted() {
    this.retrieveStores();
  }
}
</script>

<style scoped>
.list {
  text-align: left;
  margin: auto;
}
</style>
