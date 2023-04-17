<template>
    <div>
        <h1>部门管理功能</h1>
        <div class="centent">
            <vxe-table
                round
                border
                height="300"
                :row-config="{isCurrent: true, isHover: true}"
                :seq-config="tableSeq3"
                :data="tableData3">
                <vxe-column type="seq" title="序号" width="80"></vxe-column>
                <vxe-column field="name" title="Name" sortable></vxe-column>
                <vxe-column field="sex" title="Sex"></vxe-column>
                <vxe-column field="age" title="Age"></vxe-column>
                <vxe-column field="address" title="Address" show-overflow></vxe-column>
            </vxe-table>
        </div>
    </div>
</template>
<script>
 import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup () {
    const tableData3 = ref([
      { id: 10001, name: 'Test1', role: 'Develop', sex: 'Man', age: 28, address: 'test abc' },
      { id: 10002, name: 'Test2', role: 'Test', sex: 'Women', age: 22, address: 'Guangzhou' },
      { id: 10003, name: 'Test3', role: 'PM', sex: 'Man', age: 32, address: 'Shanghai' },
      { id: 10004, name: 'Test4', role: 'Designer', sex: 'Women', age: 23, address: 'test abc' },
      { id: 10005, name: 'Test5', role: 'Develop', sex: 'Women', age: 30, address: 'Shanghai' },
      { id: 10006, name: 'Test6', role: 'Designer', sex: 'Women', age: 21, address: 'test abc' },
      { id: 10007, name: 'Test7', role: 'Test', sex: 'Man', age: 29, address: 'test abc' },
      { id: 10008, name: 'Test8', role: 'Develop', sex: 'Man', age: 35, address: 'test abc' }
    ])

    const tableSeq3 = ref({
      seqMethod ({ rowIndex }) {
        return `NO${rowIndex + 1000}`
      }
    })

    return {
      tableData3,
      tableSeq3
    }
  }
})
</script>
<style>
.centent{
    padding: 0 1%;
}
</style>