<template>
  <div>
<<<<<<< HEAD
    <v-card>
      <v-container fluid>
        <v-row align="center">
          <v-col cols="12">
            <v-autocomplete
              v-model="mission.missionType"
              :items="items"
              dense
              chips
              small-chips
              label="Solo"
              multiple
              solo
            ></v-autocomplete>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
    <v-text-field v-model="mission.missionTitle" :rules="rules"></v-text-field>
    <v-container class="px-0" fluid>
      <v-checkbox
        v-model="mission.missionOpen"
        :label="`공개방 설정 여부: ${mission.missionOpen.toString()}`"
      ></v-checkbox>
    </v-container>
    <v-text-field
      v-model="mission.missionLimitPersonnel"
      label="인원수를 1~8사이의 숫자로 입력해주세요"
      :rules="personrules"
      type="number"
      min="1"
      hide-details="auto"
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
    ></v-text-field>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="mission.missionContent"
              :rules="descriprules"
              counter
              maxlength="25"
              hint="This field uses maxlength attribute"
              label="Limit exceeded"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-text-field
      label="참가하기 위한 Point를 숫자로 입력해주세요"
      :rules="pointrules"
      type="number"
      min="1"
      hide-details="auto"
      v-model="mission.missionPoint"
      oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
    ></v-text-field>
    <br />
    <!-- <v-row justify="center">
      <v-date-picker v-model="mission.missionOpen"></v-date-picker>
    </v-row> -->
    <v-btn @click="createmission">Create</v-btn>
=======
    <v-container>
      <v-row class="py-3 pl-3">
        <!-- 뒤로 가기 버튼 -->
        <v-btn @click="goback" icon>
          <v-icon> mdi-arrow-left</v-icon>
        </v-btn>
      </v-row>
      <v-row justify="center" align="center">
        <h3>새로운 Mission 생성하기</h3>
      </v-row>
      <v-row class="px-7 pt-7">
        <!-- 주제 -->
        <v-autocomplete
          v-model="mission.missionType"
          :items="items"
          dense
          chips
          small-chips
          label="Mission과 가장 유사한 주제 선택"
          solo
        ></v-autocomplete>
      </v-row>
      <v-row class="px-7 py-0">
        <v-text-field
          v-model="mission.missionTitle"
          :rules="rules"
          label="미션 제목을 입력해주세요 (4 ~ 30자)"
        ></v-text-field>
      </v-row>
      <v-row class="px-2 pt-3">
        <v-col cols="8">
          <p>공개방 여부를 선택해주세요.</p>
          <h5>비공개방: 코드로만 참가 가능</h5>
        </v-col>
        <v-col>
          <v-switch
            color="#457b9d"
            v-model="mission.missionOpen"
            :label="label[mission.missionOpen == true ? 0 : 1]"
          ></v-switch>
        </v-col>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-col cols="5">
          참여 인원 : {{ mission.missionLimitPersonnel }}명
        </v-col>
        <v-slider
          v-model="mission.missionLimitPersonnel"
          :tick-labels="ticksLabels"
          :max="8"
          :min="2"
          step="1"
          ticks="always"
          tick-size="2"
          color="#457b9d"
        ></v-slider>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-col class="pt-6" cols="6"> 미션 참가 포인트 : </v-col>
        <v-col class="px-0" cols="4">
          <v-text-field
            label="100 이상"
            class="point"
            :rules="pointrules"
            outlined
            type="number"
            min="1"
            hide-details="auto"
            v-model="mission.missionPoint"
            oniput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g,'');"
          ></v-text-field>
        </v-col>
        <v-col class="px-0 pt-6"> Point </v-col>
      </v-row>
      <v-row class="px-4 pt-3">
        <v-textarea
          v-model="mission.missionContent"
          :rules="descriprules"
          maxlength="300"
          outlined
          label="미션 설명, 달성 조건 등을 기재해 주세요."
          height="120"
        ></v-textarea>
      </v-row>
      <v-row class="pr-4" justify="end">
        <v-btn outlined @click="createmission" color="#fa183e">
          생성하기
        </v-btn>
      </v-row>
    </v-container>
    <!-- create Dialog -->
    <v-dialog v-model="createDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>새로운 미션 생성하기</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.createMsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="refresh"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- createFail Dialog -->
    <v-dialog v-model="FailDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>새로운 미션 생성하기</h5>
        </v-card-title>
        <v-card-title>
          <h6>
            보유한 포인트가 참가 포인트보다 많아야 미션을 생성할 수 있습니다.
          </h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="FailDialog = false"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
<<<<<<< HEAD
    mission: {
      // missionType, missionOpen 수정필요
      missionTitle: null,
      missionType: "LIFE",
      missionPoint: null,
      missionLimitPersonnel: null,
      missionContent: null,
      missionOpen: "OPEN_ROOM",
    },
    pointrules: [
      (value) => !!value || "입력해주세요",
      (value) => (value && value.length >= 3) || "Min 3 characters",
    ],
    personrules: [
      (value) => !!value || "입력해주세요",
      (value) =>
        (value && value >= 1 && value <= 8) || "1~8사이의 숫자로 입력해주세요",
    ],
    items: ["미라클모닝", "공부", "코딩", "운동", "기타"],

    rules: [
      (value) => !!value || "제목을 입력해주세요",
      (value) => (value || "").length <= 20 || "Max 20 characters",
    ],
    slider: 8,
    title: "Preliminary report",
    description: "이방은 어쩌구 저쩌구 솰라솰라",
    descriprules: [(v) => v.length <= 25 || "Max 25 characters"],
  }),
=======
    ticksLabels: [2, 3, 4, 5, 6, 7, 8],
    label: ["공개", "비공개"],
    createDialog: false,
    FailDialog: false,
    createMsg: "",
    missionNum: null,
    mission: {
      gotoUnactDialog: false,
      missionTitle: null,
      missionType: null,
      missionPoint: null,
      missionLimitPersonnel: null,
      missionContent: null,
      missionOpen: true,
    },
    userInfo: [],
    pointrules: [
      (value) => !!value || "필수 항목입니다.",
      (value) => (value && value.length >= 3) || "",
    ],
    items: ["LIFE", "EXERCISE", "STUDY", "MEETING", "ETC"],

    rules: [
      (value) => !!value || "필수 항목입니다.",
      (value) =>
        (value && (value || "").length >= 4 && (value || "").length <= 30) ||
        "",
    ],
    slider: 8,
    title: "Preliminary report",
    description: "해당 미션 설명",
    descriprules: [
      (v) =>
        (v && v.length <= 300 && v.length >= 6) ||
        "6 ~ 300자까지 입력 가능합니다.",
    ],
  }),
  created() {
    var vm = this;
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${sessionStorage.getItem("token")}`;
    axios
      .get(`api/user/info`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then(function (response) {
        vm.userInfo = response.data;
      })
      .catch(function (err) {
        console.log(err);
      });
  },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
  methods: {
<<<<<<< HEAD
=======
    refresh() {
      if (
        this.createMsg ===
        "미션 성공 인증을 위한 시간을 설정하면 해당 미션이 활성화됩니다."
      )
        window.location.href = `/unactivated/${this.missionNum}`;
      else this.createDialog = false;
    },
    goback() {
      this.$router.go(-1);
    },
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    createmission() {
      if (
        !this.mission.missionTitle ||
        !this.mission.missionPoint ||
        !this.mission.missionLimitPersonnel ||
        !this.mission.missionContent
      ) {
<<<<<<< HEAD
        console.log(this.mission.missionTitle);
        console.log(this.mission.missionType);
        console.log(this.mission.missionPoint);
        console.log(this.mission.missionLimitPersonnel);
        console.log(this.mission.missionContent);
        console.log(this.mission.missionOpen);
        alert("정보를 다 입력해주세요");
        return;
      }
      axios
        .post(`api/mission`, {
          missionTitle: this.mission.missionTitle,
          missionType: this.mission.missionType,
          missionPoint: this.mission.missionPoint,
          missionLimitPersonnel: this.mission.missionLimitPersonnel,
          missionContent: this.mission.missionContent,
          missionOpen: this.mission.missionOpen,
        })
        .then(() => {
          alert("미션생성완료");
        })
        .catch(() => {
          alert("미션생성실패");
          console.log(this.mission.missionTitle);
          console.log(this.mission.missionType);
          console.log(this.mission.missionPoint);
          console.log(this.mission.missionLimitPersonnel);
          console.log(this.mission.missionContent);
          console.log(this.mission.missionOpen);
        });
=======
        this.createMsg = "필수 정보를 모두 입력해주세요";
        this.createDialog = true;
      } else {
        if (this.userInfo.userPoint < this.mission.missionPoint) {
          this.FailDialog = true;
        } else {
          axios
            .post(`api/mission`, {
              missionLeaderId: this.userInfo.userId,
              missionTitle: this.mission.missionTitle,
              missionType: this.mission.missionType,
              missionPoint: this.mission.missionPoint,
              missionLimitPersonnel: this.mission.missionLimitPersonnel,
              missionContent: this.mission.missionContent,
              missionOpen: this.mission.missionOpen ? 0 : 1,
            })
            .then((response) => {
              this.createMsg =
                "미션 성공 인증을 위한 시간을 설정하면 해당 미션이 활성화됩니다.";
              this.missionNum = response.data;
            })
            .catch(() => {
              this.createMsg = "미션 생성에 실패하였습니다.";
            });
          this.createDialog = true;
        }
      }
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    },
  },
};
</script>
<<<<<<< HEAD

<style></style>
=======
<style>
.point {
  height: 10%;
  width: 100%;
}
</style>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
