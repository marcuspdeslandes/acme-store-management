import http from "../http-common";

class StoreDataService {
  getAll(page: number) {
    return http.get(`/store/?page=${page}&pageSize=10`);
  }

  update(id: string, data: any) {
    return http.put(`/store/id/${id}`, data);
  }

  findByName(storeName: string) {
    return http.get(`/store/name/${storeName}?page=0&pageSize=10`);
  }
}

export default new StoreDataService();
