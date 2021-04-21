export interface StoreListState {
  content: Array<StoreListResultRow>;
  totalElements: 0;
  pagination: PaginationStore;
  filter: string;
  storeIdToChange: number;
  storeNameChanged: string;
}

export interface StoreListResultRow {
  id: string;
  code: string;
  description: string;
  name: string;
  openingDate: string;
  storeType: string;
  storeSeason: string;
  specialField1: string;
  specialField2:string;
}

export interface PaginationStore {
  pageNumber: number,
  pageSize: number
}

export const SET_NAME_TO_CHANGE_VALUE = 'SET_NAME_TO_CHANGE_VALUE';
export const STORE_LIST_CHANGE_NAME_SUCCESS = 'STORE_LIST_CHANGE_NAME_SUCCESS';
export const STORE_LIST_CHANGE_NAME_FAILURE = 'STORE_LIST_CHANGE_NAME_FAILURE';
export const STORE_LIST_SUCCESS = 'STORE_LIST_SUCCESS';
export const STORE_LIST_FAILURE = 'STORE_LIST_FAILURE';
export const SET_STORE_ID_TO_CHANGE = 'SET_STORE_ID_TO_CHANGE';
export const SET_FILTER_VALUE = 'SET_FILTER_VALUE';

export interface CommandAction {
  type:
    | typeof STORE_LIST_CHANGE_NAME_FAILURE
    | typeof STORE_LIST_FAILURE
    | typeof STORE_LIST_SUCCESS
    | typeof SET_FILTER_VALUE
    | typeof SET_STORE_ID_TO_CHANGE
    | typeof SET_NAME_TO_CHANGE_VALUE
    | typeof STORE_LIST_CHANGE_NAME_SUCCESS;
  payload?: any;
}
