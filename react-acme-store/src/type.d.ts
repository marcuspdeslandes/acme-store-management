interface IStore {
  id: number;
  code: string;
  description: string;
  name: string;
  openingDate: string;
  storeType: string;
}

type StoreState = {
  stores: IStore[];
};

type StoreAction = {
  type: string;
  payload: any;
};

type DispatchType = (args: StoreAction) => StoreAction;
