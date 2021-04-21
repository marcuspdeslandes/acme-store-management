import {StoreListState, CommandAction } from './types';
import mapperStore from "./mapperStore";

const initialState: StoreListState = {
  content: [],
  totalElements: 0,
  pagination: {
    pageNumber: 0,
    pageSize: 5
  },
  filter: "",
  storeIdToChange: 0,
  storeNameChanged: ""
};

export default function(state = initialState, action: CommandAction) {
  switch (action.type) {
    case 'STORE_LIST_FAILURE':
    case 'STORE_LIST_CHANGE_NAME_FAILURE':
      return { ...state };
    case 'STORE_LIST_SUCCESS':
      return { ...state, content: action.payload.data.map(mapperStore) , totalElements: action.payload.totalElements };
    case 'SET_FILTER_VALUE':
      return { ...state, filter: action.payload };
    case 'SET_NAME_TO_CHANGE_VALUE':
      return { ...state, storeNameChanged: action.payload };
    case 'SET_STORE_ID_TO_CHANGE':
      return { ...state, storeIdToChange: action.payload.id, storeNameChanged: action.payload.name };
    case 'STORE_LIST_CHANGE_NAME_SUCCESS':
      return { ...state, storeIdToChange: 0, storeNameChanged: "" };
    default:
      return { ...state };
  }
}
