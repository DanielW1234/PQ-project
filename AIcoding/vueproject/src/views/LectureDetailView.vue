<script setup>
import { onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
onMounted(() => {
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  const role = user.role || 'audience'
  if (role === 'audience') {
    router.replace({ name: 'AudienceLectureDetail', params: { id: route.params.id } })
  } else {
    router.replace({ name: 'OrganizerLectureDetail', params: { id: route.params.id } })
  }
})
</script> 