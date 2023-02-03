import IonDoList from "./IonDoList";

const data = [{ name: "John", age: 25 }, { name: "Doe", age: 32 }];
type DataType = { name: string, age: number }[]
const headers: (keyof DataType[0])[] = ["name", "age"];


const MyComponent = () => (
  <div>
    <IonDoList<typeof data[0]> data={data} headers={headers} />
  </div>
);
export default MyComponent;