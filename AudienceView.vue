<template>
  <div class="audience-view">
    <div class="view-header">
      <h2>听众答题页面</h2>
      <p>请回答以下问题，系统将实时统计您的答题情况</p>
    </div>
    
    <div class="audience-quiz">
      <div class="quiz-question">1. PopQuiz的主要功能是什么？</div>
      <div class="quiz-options">
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[0] === 0 }"
          @click="selectAnswer(0, 0)"
        >
          自动生成课堂测验题目
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[0] === 1 }"
          @click="selectAnswer(0, 1)"
        >
          管理学生考勤
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[0] === 2 }"
          @click="selectAnswer(0, 2)"
        >
          创建课程PPT
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[0] === 3 }"
          @click="selectAnswer(0, 3)"
        >
          在线视频会议
        </div>
      </div>
    </div>
    
    <div class="audience-quiz">
      <div class="quiz-question">2. AI生成测验题目的优势是什么？</div>
      <div class="quiz-options">
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[1] === 0 }"
          @click="selectAnswer(1, 0)"
        >
          可以及时捕捉演讲内容
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[1] === 1 }"
          @click="selectAnswer(1, 1)"
        >
          完全替代教师出题
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[1] === 2 }"
          @click="selectAnswer(1, 2)"
        >
          生成题目不需要验证
        </div>
        <div 
          class="quiz-option" 
          :class="{ selected: selectedAnswers[1] === 3 }"
          @click="selectAnswer(1, 3)"
        >
          节省硬件资源
        </div>
      </div>
    </div>
    
    <button class="btn btn-primary" @click="submitAnswers">
      提交答案
    </button>
    
    <div class="quiz-result" v-if="submitted">
      <h3>答题结果</h3>
      <p>答对题目数: {{ correctCount }} / {{ quizzes.length }}</p>
      <p>正确率: {{ accuracyRate }}%</p>
      <p>在听众中排名: {{ ranking }}</p>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const quizzes = [
      {
        id: 'quiz-001',
        question: 'PopQuiz的主要功能是什么？',
        options: [
          '自动生成课堂测验题目',
          '管理学生考勤',
          '创建课程PPT',
          '在线视频会议'
        ],
        correctAnswer: 0
      },
      {
        id: 'quiz-002',
        question: 'AI生成测验题目的优势是什么？',
        options: [
          '可以及时捕捉演讲内容',
          '完全替代教师出题',
          '生成题目不需要验证',
          '节省硬件资源'
        ],
        correctAnswer: 0
      }
    ];
    
    const selectedAnswers = ref([]);
    const submitted = ref(false);
    const correctCount = ref(0);
    const accuracyRate = ref(0);
    const ranking = ref('未排名');
    
    const selectAnswer = (quizIndex, optionIndex) => {
      selectedAnswers.value[quizIndex] = optionIndex;
    };
    
    const submitAnswers = () => {
      correctCount.value = 0;
      quizzes.forEach((quiz, index) => {
        if (selectedAnswers.value[index] === quiz.correctAnswer) {
          correctCount.value++;
        }
      });
      
      accuracyRate.value = Math.round((correctCount.value / quizzes.length) * 100);
      
      // 简单排名算法
      if (accuracyRate.value >= 90) ranking.value = '前10%';
      else if (accuracyRate.value >= 75) ranking.value = '前20%';
      else if (accuracyRate.value >= 50) ranking.value = '前50%';
      else ranking.value = '后50%';
      
      submitted.value = true;
    };
    
    return {
      quizzes,
      selectedAnswers,
      submitted,
      correctCount,
      accuracyRate,
      ranking,
      selectAnswer,
      submitAnswers
    };
  }
}
</script>

<style scoped>
.audience-view {
  background: white;
  border-radius: 16px;
  box-shadow: var(--card-shadow);
  padding: 40px;
  max-width: 800px;
  margin: 20px auto;
}

.view-header {
  text-align: center;
  margin-bottom: 30px;
}

.view-header h2 {
  font-size: 1.8rem;
  color: var(--dark);
  margin-bottom: 10px;
}

.view-header p {
  color: var(--gray);
  font-size: 1.1rem;
}

.audience-quiz {
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid var(--border);
  border-radius: 12px;
}

.quiz-question {
  font-size: 1.2rem;
  font-weight: 500;
  margin-bottom: 15px;
}

.quiz-options {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
}

.quiz-option {
  padding: 12px 15px;
  border: 1px solid var(--border);
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.quiz-option:hover {
  background: rgba(67, 97, 238, 0.05);
  border-color: var(--primary);
}

.quiz-option.selected {
  background: rgba(67, 97, 238, 0.1);
  border-color: var(--primary);
  font-weight: bold;
}

.quiz-result {
  background: var(--light);
  border-radius: 12px;
  padding: 20px;
  margin-top: 20px;
  text-align: center;
}
</style>
