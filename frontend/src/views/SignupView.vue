<template>
<<<<<<< HEAD
  <v-container style="width: 450px">
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert v-if="isError" type="error">
          {{ errorMsg }}
        </v-alert>
        <v-card>
          <v-toolbar flat color="indigo">
            <v-toolbar-title
              ><span class="white--text">회원가입</span></v-toolbar-title
            >
          </v-toolbar>
          <div class="pa-5">
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="user.email"
                :rules="emailRules"
                label="Enter E-mail"
                required
              ></v-text-field>

              <v-text-field
                v-model="user.userid"
                label="userid"
                required
              ></v-text-field>
              중복검사
              <v-icon @click="idcheck">mdi-check-bold</v-icon>

              <v-text-field
                v-model="user.avatar"
                label="Avatar"
                required
              ></v-text-field>

              <v-text-field
                v-model="user.usernickname"
                :counter="10"
                :rules="nameRules"
                label="Name"
                required
              ></v-text-field>

              <v-text-field
                v-model="user.userpwd"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <v-text-field
                v-model="chkPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password Again"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <h6 v-if="sameChk(chkPassword)" class="mb-5 teal--text accent-3">
                Please create the two passwords identical.
              </h6>
              <h6 v-else class="mb-5 red--text lighten-2">
                Please create the two passwords identical.
              </h6>

              <div class="mt-3 d-flex flex-row-reverse">
                <v-btn color="error" class="mr-4" @click="reset"> 리셋 </v-btn>

                <v-btn
                  :disabled="!valid"
                  color="blue"
                  class="mr-4"
                  @click="register()"
                >
                  회원가입
                </v-btn>
              </div>
            </v-form>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
=======
  <div>
    <v-container>
      <v-row class="py-3">
        <v-col cols="2">
          <!-- 뒤로 가기 버튼 -->
          <v-btn @click="goback" icon>
            <v-icon> mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
        <v-col class="pt-4 pl-12 pr-0" cols="10">
          <h3>OctopUS 회원 가입</h3>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-alert v-if="isError" type="error">
          {{ errorMsg }}
        </v-alert>
      </v-row>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-row class="pl-3 pt-8">
          <h4>이메일을 통해 본인임을 인증합니다.</h4>
        </v-row>
        <v-row>
          <v-col class="py-0 pr-3" cols="9">
            <v-text-field
              v-model="user.email"
              :rules="emailRules"
              label="사용가능한 이메일을 입력해주세요."
            ></v-text-field>
          </v-col>
          <v-col class="px-0" cols="3">
            <div v-if="codeChk == false">
              <v-btn color="#fa183e" outlined class="px-0" @click="sendemail"
                >인증하기</v-btn
              >
            </div>
            <div v-else-if="codeChk == true">
              <v-btn
                disabled
                color="success"
                outlined
                class="px-0"
                @click="sendemail"
                >인증하기</v-btn
              >
            </div>
          </v-col>
        </v-row>
        <v-row v-if="issendemail">
          <v-col class="py-0 pr-3" cols="8">
            <v-text-field
              v-model="aouthcode"
              required
              label="인증번호 입력"
            ></v-text-field>
          </v-col>
          <v-col class="px-0" cols="3">
            <div v-if="codeChk == false">
              <v-btn outlined color="#fa183e" class="px-0" @click="codecheck"
                >인증번호 확인</v-btn
              >
            </div>
            <div v-else-if="true">
              <v-btn
                disabled
                outlined
                color="success"
                class="px-0"
                @click="codecheck"
                >인증번호 확인</v-btn
              >
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pt-5 pb-0" cols="8">
            <!-- id input -->
            <v-text-field
              v-model="user.userid"
              label="아이디를 입력해주세요."
              :rules="[rules.required]"
              @change="userIdChk = false"
            ></v-text-field>
          </v-col>
          <v-col cols="4" class="pt-8">
            <div v-if="userIdChk == false || user.userid == null">
              <v-btn color="#fa183e" outlined class="px-0" @click="idcheck"
                >중복 검사</v-btn
              >
            </div>
            <div v-else-if="userIdChk == true">
              <v-btn
                outlined
                color="success"
                disabled
                class="px-0"
                @click="idcheck"
                >중복 검사</v-btn
              >
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pt-5 pb-0">
            <!-- password input -->
            <v-text-field
              v-model="user.userpwd"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="show1 ? 'text' : 'password'"
              label="비밀번호를 설정해주세요."
              hint="8자 이상 입력하세요"
              @click:append="show1 = !show1"
              counter
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="py-0">
            <!-- password input again -->
            <v-text-field
              v-model="chkPassword"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="show2 ? 'text' : 'password'"
              label="비밀번호를 다시 한 번 입력해주세요."
              hint="8자 이상 입력하세요"
              @click:append="show2 = !show2"
              counter
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row justify="center">
          <h5
            v-if="!sameChk(chkPassword) && user.userpwd != null"
            class="pink--text"
          >
            비밀번호가 같지 않습니다. 다시 한 번 확인해주세요.
          </h5>
        </v-row>
        <v-row>
          <v-col class="pb-0 pt-5" cols="8">
            <v-form ref="nicknameForm" lazy-validation>
              <!-- nickname input -->
              <v-text-field
                v-model="user.usernickname"
                :counter="10"
                label="닉네임을 입력해주세요."
                :rules="nameRules"
                @change="userNickChk = false"
              ></v-text-field
            ></v-form>
          </v-col>
          <v-col cols="4" class="pt-8">
            <div v-if="userNickChk == false || user.usernickname == null">
              <v-btn
                color="#fa183e"
                outlined
                class="px-0"
                @click="nickcheck"
                @keyup.enter="nickcheck"
              >
                중복 검사
              </v-btn>
            </div>
            <div v-else-if="userNickChk == true">
              <v-btn
                disabled
                outlined
                class="px-0"
                @click="nickcheck"
                @keyup.enter="nickcheck"
              >
                중복 검사
              </v-btn>
            </div>
            <div></div>
          </v-col>
        </v-row>
        <v-row class="pt-10" justify="center">
          <h5>옥순이 캐릭터를 골라주세요.</h5>
        </v-row>
        <v-row justify="center">
          <h6>차후 포인트 차감 후 변경 가능합니다.</h6>
        </v-row>
        <v-row justify="center">
          <v-img
            max-width="30%"
            :src="
              require(`../assets/img/Ocsoon/Character/${this.user.avatar}.png`)
            "
          >
            <v-img src="../assets/img/Ocsoon/Face/0.png"></v-img>
          </v-img>
        </v-row>
        <v-row justify="center" class="pt-3 pb-0">
          <v-btn max-height="30" color="#fa183e" @click="changeColor(0)" />
          <v-btn max-height="30" color="#ffbbed" @click="changeColor(1)" />
          <v-btn max-height="30" color="#ffec00" @click="changeColor(2)" />
          <v-btn max-height="30" color="#b9ffb2" @click="changeColor(3)" />
        </v-row>
        <v-row justify="center" class="py-0">
          <v-btn max-height="30" color="#a5bbff" @click="changeColor(7)" />
          <v-btn max-height="30" color="#003fff" @click="changeColor(4)" />
          <v-btn max-height="30" color="#d2a1ff" @click="changeColor(5)" />
          <v-btn max-height="30" color="#8500ff" @click="changeColor(6)" />
        </v-row>
        <v-row class="px-3 pt-8">
          <v-card class="overflow-y-auto" max-height="250">
            <v-card-text>
              <div class="mb-4">
                <h2 class="pl-7">OctopUS 개인정보 처리 방침</h2>
                <br />
                ('OctopUS'이하 'OctopUS')은(는) 「개인정보 보호법」 제30조에
                따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고
                원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보
                처리방침을 수립·공개합니다.
                <br /><br />
                ○ 이 개인정보처리방침은 2022년 1월 1부터 적용됩니다.
                <br /><br />
                제1조(개인정보의 처리 목적)
                <br />
                [ OctopUS ]('OctopUS'이하 'OctopUS')은(는) 다음의 목적을 위하여
                개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적
                이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는
                「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한
                조치를 이행할 예정입니다.
                <br />
                1. 홈페이지 회원가입 및 관리
                <br />
                회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증,
                회원자격 유지·관리, 서비스 부정이용 방지 목적으로 개인정보를
                처리합니다.
                <br />
                2. 재화 또는 서비스 제공
                <br />
                서비스 제공, 콘텐츠 제공, 본인인증, 요금결제·정산을 목적으로
                개인정보를 처리합니다.
                <br /><br />
                제2조(개인정보의 처리 및 보유 기간)
                <br />
                ① [ OctopUS ]은(는) 법령에 따른 개인정보 보유·이용기간 또는
                정보주체로부터 개인정보를 수집 시에 동의받은 개인정보
                보유·이용기간 내에서 개인정보를 처리·보유합니다.
                <br />
                ② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.
                <br /><br />
                1.[ 홈페이지 회원가입 및 관리 ] [ 홈페이지 회원가입 및 관리 ]와
                관련한 개인정보는 수집.이용에 관한 동의일로부터(1년)까지 위
                이용목적을 위하여 보유.이용됩니다.
                <br /><br />
                제3조(처리하는 개인정보의 항목)
                <br />
                ① [ OctopUS ]은(는) 다음의 개인정보 항목을 처리하고 있습니다.
                <br />
                1[ 홈페이지 회원가입 및 관리 ]
                <br />
                필수항목 : 이메일, 비밀번호, 로그인ID, 서비스 이용 기록,
                결제기록
                <br /><br />
                제4조(개인정보의 제3자 제공에 관한 사항)
                <br />
                ① [ OctopUS ]은(는) 개인정보를 제1조(개인정보의 처리 목적)에서
                명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한
                규정 등 「개인정보 보호법」 제17조 및 제18조에 해당하는 경우에만
                개인정보를 제3자에게 제공합니다.
                <br /><br />
                제5조(개인정보처리의 위탁에 관한 사항)
                <br />
                ① [ OctopUS ]은(는) 원활한 개인정보 업무처리를 위하여 다음과
                같이 개인정보 처리업무를 위탁하고 있습니다.
                <br /><br />
                제6조(개인정보의 파기절차 및 파기방법)
                <br />
                ① [ OctopUS ] 은(는) 개인정보 보유기간의 경과, 처리목적 달성 등
                개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를
                파기합니다.
                <br />
                ② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나
                처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를
                계속 보존하여야 하는 경우에는, 해당 개인정보를 별도의
                데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.
                <br />
                ③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.
                <br />
                1. 파기절차
                <br />
                [ OctopUS ] 은(는) 파기 사유가 발생한 개인정보를 선정하고, [
                OctopUS ] 의 개인정보 보호책임자의 승인을 받아 개인정보를
                파기합니다.
                <br /><br />
                제7조(정보주체와 법정대리인의 권리·의무 및 그 행사방법에 관한
                사항)
                <br />
                ① 정보주체는 OctopUS에 대해 언제든지 개인정보
                열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.
                <br />
                ② 제1항에 따른 권리 행사는OctopUS에 대해 「개인정보 보호법」
                시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을
                통하여 하실 수 있으며 OctopUS은(는) 이에 대해 지체 없이
                조치하겠습니다.
                <br />
                ③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은
                자 등 대리인을 통하여 하실 수 있습니다.이 경우 “개인정보 처리
                방법에 관한 고시(제2020-7호)” 별지 제11호 서식에 따른 위임장을
                제출하셔야 합니다.
                <br />
                ④ 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조
                제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수
                있습니다.
                <br />
                ⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가
                수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수
                없습니다.
                <br />
                ⑥ OctopUS은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의
                요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나
                정당한 대리인인지를 확인합니다.
                <br /><br />
                제8조(개인정보의 안전성 확보조치에 관한 사항)
                <br />
                [ OctopUS ]은(는) 개인정보의 안전성 확보를 위해 다음과 같은
                조치를 취하고 있습니다.
                <br />
                1. 개인정보의 암호화
                <br />
                이용자의 개인정보는 비밀번호는 암호화 되어 저장 및 관리되고
                있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송
                데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도
                보안기능을 사용하고 있습니다.
                <br />
                2. 접속기록의 보관 및 위변조 방지
                <br />
                개인정보처리시스템에 접속한 기록을 최소 1년 이상 보관, 관리하고
                있으며,다만, 5만명 이상의 정보주체에 관하여 개인정보를
                추가하거나, 고유식별정보 또는 민감정보를 처리하는 경우에는
                2년이상 보관, 관리하고 있습니다.
                <br />
                또한, 접속기록이 위변조 및 도난, 분실되지 않도록 보안기능을
                사용하고 있습니다.
                <br />
                3. 개인정보에 대한 접근 제한
                <br />
                개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의
                부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한
                조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단
                접근을 통제하고 있습니다.
                <br /><br />
                제9조(개인정보를 자동으로 수집하는 장치의 설치·운영 및 그 거부에
                관한 사항)
                <br />
                OctopUS 은(는) 정보주체의 이용정보를 저장하고 수시로 불러오는
                ‘쿠키(cookie)’를 사용하지 않습니다.
                <br /><br />
                제10조(행태정보의 수집·이용·제공 및 거부 등에 관한 사항)
                <br />
                행태정보의 수집·이용·제공 및 거부등에 관한 사항
                <br />
                [개인정보처리자명]은(는) 온라인 맞춤형 광고 등을 위한 행태정보를
                수집·이용·제공하지 않습니다.
                <br /><br />
                제11조(추가적인 이용·제공 판단기준)
                <br />
                [ OctopUS ] 은(는) ｢개인정보 보호법｣ 제15조제3항 및
                제17조제4항에 따라 ｢개인정보 보호법 시행령｣ 제14조의2에 따른
                사항을 고려하여 정보주체의 동의 없이 개인정보를 추가적으로
                이용·제공할 수 있습니다.
                <br />
                이에 따라 [ OctopUS ] 가(이) 정보주체의 동의 없이 추가적인
                이용·제공을 하기 위해서 다음과 같은 사항을 고려하였습니다.
                <br /><br />
                ▶ 개인정보를 추가적으로 이용·제공하려는 목적이 당초 수집 목적과
                관련성이 있는지 여부
                <br /><br />
                ▶ 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 추가적인
                이용·제공에 대한 예측 가능성이 있는지 여부
                <br /><br />
                ▶ 개인정보의 추가적인 이용·제공이 정보주체의 이익을 부당하게
                침해하는지 여부
                <br /><br />
                ▶ 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지
                여부
                <br /><br />
                ※ 추가적인 이용·제공 시 고려사항에 대한 판단기준은 사업자/단체
                스스로 자율적으로 판단하여 작성·공개함
                <br /><br />
                제12조(가명정보를 처리하는 경우 가명정보 처리에 관한 사항)
                <br />
                [ OctopUS ] 은(는) 다음과 같은 목적으로 가명정보를 처리하고
                있습니다.
                <br />
                ▶ 법 제28조의4(가명정보에 대한 안전조치 의무 등)에 따른
                가명정보의 안전성 확보조치에 관한 사항
                <br /><br />
                제13조 (개인정보 보호책임자에 관한 사항)
                <br />
                ① OctopUS 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고,
                개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을
                위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.
                <br /><br />
                제14조(국내대리인의 지정)
                <br />
                정보주체는 ｢개인정보 보호법｣ 제39조의11에 따라 지정된 [ OctopUS
                ]의 국내대리인에게 개인정보 관련 고충처리 등의 업무를 위하여
                연락을 취할 수 있습니다. [ OctopUS ]은(는) 정보주체의 개인정보
                관련 고충처리 등 개인정보 보호책임자의 업무 등을 신속하게 처리할
                수 있도록 노력하겠습니다.
                <br /><br />
                제16조(정보주체의 권익침해에 대한 구제방법)
                <br />
                정보주체는 개인정보침해로 인한 구제를 받기 위하여
                개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터
                등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타
                개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기
                바랍니다.
                <br />
                1. 개인정보분쟁조정위원회 : (국번없이) 1833-6972
                (www.kopico.go.kr)
                <br />
                2. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)
                <br />
                3. 대검찰청 : (국번없이) 1301 (www.spo.go.kr)
                <br />
                4. 경찰청 : (국번없이) 182 (ecrm.cyber.go.kr)
                <br />
                「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의
                정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에
                대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는
                이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을
                청구할 수 있습니다.
                <br />
                ※ 행정심판에 대해 자세한 사항은
                중앙행정심판위원회(www.simpan.go.kr) 홈페이지를 참고하시기
                바랍니다.
                <br /><br />
                제18조(개인정보 처리방침 변경)
                <br />
                ① 이 개인정보처리방침은 2022년 8월 1부터 적용됩니다.
              </div>
            </v-card-text>
          </v-card>
        </v-row>
        <v-row justify="center" class="pt-0">
          <v-checkbox v-model="agreement">
            <template v-slot:label>
              <h5>OctopUS의 개인정보 처리방침에 동의하고 가입합니다.</h5>
            </template>
          </v-checkbox>
        </v-row>

        <v-row class="pt-1">
          <v-col>
            <v-btn outlined @click="reset" color="red">Reset</v-btn>
          </v-col>
          <v-col></v-col>
          <v-col>
            <v-btn color="#ffadad" @click="register()">회원가입하기</v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
    <!-- emailDialog -->
    <v-dialog v-model="EmailDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>E-mail</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.msg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="EmailDialog = false">
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 이메일인증Dialog -->
    <v-dialog v-model="EmailAuthenDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>E-mail</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.authenmsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="EmailAuthenDialog = false">
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 아이디중복검사Dialog -->
    <v-dialog v-model="IDDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>ID</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.idmsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="IDDialog = false"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 닉네임중복검사Dialog -->
    <v-dialog v-model="NickDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>닉네임</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.nickmsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="NickDialog = false"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 회원가입Dialog -->
    <v-dialog v-model="registerDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <h5>회원가입</h5>
        </v-card-title>
        <v-card-title>
          <h6>{{ this.registermsg }}</h6>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#fa183e" text @click="registerDialog = false">
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
</template>

<script>
import axios from "axios";
<<<<<<< HEAD
// import { apiInstance } from "../api/index.js";

// import http from "../utils/http-common.js";

// const api = apiInstance();

=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
export default {
  data: () => ({
    user: {
      userid: null,
      userpwd: null,
      usernickname: null,
      email: null,
<<<<<<< HEAD
      avatar: null,
    },
    userIdChk: true,

    valid: false,
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
=======
      avatar: 0,
    },
    aouthcode: null,
    EmailDialog: false,
    EmailAuthenDialog: false,
    IDDialog: false,
    NickDialog: false,
    registerDialog: false,
    msg: "",
    authenmsg: "",
    idmsg: "",
    nickmsg: "",
    registermsg: "",
    issendemail: false,
    userIdChk: true,
    userNickChk: true,
    codeChk: false,
    valid: false,
    agreement: false,
    avatars: [
      { num: 0 },
      { num: 1 },
      { num: 2 },
      { num: 3 },
      { num: 4 },
      { num: 5 },
      { num: 6 },
      { num: 7 },
    ],

    nameRules: [
      (v) => !!v || "필수 항목입니다.",
      (v) => (v && v.length <= 10) || "4글자이상 10글자이하로 입력해주세요",
      (v) => (v && v.length >= 4) || "4글자이상 10글자이하로 입력해주세요",
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    ],
    isError: false,
    errorMsg: "",
    emailRules: [
<<<<<<< HEAD
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
    show: false,
    chkPassword: "",
    rules: {
      required: (value) => !!value || "Required.",
      min: (v) => v.length >= 8 || "Min 8 characters",
    },
  }),
  methods: {
    idcheck() {
      axios
        .get(`api/user/idcheck/${this.user.userid}`)
        .then(({ data }) => {
          let msg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === "success") {
            msg = "사용가능한 아이디입니다.";
            this.userIdChk = true;
            alert(msg);
          } else {
            alert(msg);
            this.$router.push({ name: "signUp" });
=======
      (v) => !!v || "필수 항목입니다.",
      (v) => {
        if (v) {
          const replaceV = v.replace(/(\s*)/g, "");
          const pattern =
            /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
          return pattern.test(replaceV) || "이메일 형식이 올바르지 않습니다.";
        } else {
          return !!v || "필수 항목입니다.";
        }
      },
    ],
    show1: false,
    show2: false,
    chkPassword: "",
    rules: {
      required: (value) => !!value || "필수 항목입니다.",
      min: (v) => (v && v.length >= 8) || "8글자이상 입력해주세요.",
    },
  }),
  methods: {
    changeColor(value) {
      this.user.avatar = value;
    },
    goback() {
      this.$router.go(-1);
    },
    login() {
      this.$router.push({ name: "Login" });
    },
    idcheck() {
      axios
        .get(`api/register/check/id/${this.user.userid}`)
        .then(({ data }) => {
          this.idmsg = "중복된 아이디입니다. 다시 입력해주세요";
          if (data === false) {
            this.idmsg = "사용가능한 아이디입니다.";
            this.userIdChk = true;
          } else {
            this.userIdChk = false;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          }
        })
        .catch((error) => {
          console.log(error);
<<<<<<< HEAD
          alert("ID중복체크에 실패했습니다..");
        })
        .finally(() => {
          console.log(this.user.userid);
=======
          this.idmsg = "ID중복체크에 실패했습니다..";
        })
        .finally(() => {
          this.IDDialog = true;
        });
    },
    nickcheck() {
      const validate = this.$refs.nicknameForm.validate();
      if (validate) {
        axios
          .get(`api/register/check/nickname/${this.user.usernickname}`)
          .then(({ data }) => {
            this.nickmsg = "중복된 닉네임입니다. 다시 입력해주세요";
            if (data === false) {
              this.nickmsg = "사용가능한 닉네임입니다.";
              this.userNickChk = true;
            } else {
              this.userNickChk = false;
            }
          })
          .catch((error) => {
            console.log(error);
            this.nickmsg = "닉네임 중복검사에 실패했습니다.";
          });
      } else {
        this.nickmsg = "닉네임이 유효하지 않습니다.";
      }
      this.NickDialog = true;
    },
    codecheck() {
      axios
        .post(`api/email/check`, {
          userEmail: this.user.email,
          emailCode: this.aouthcode,
        })
        .then(({ data }) => {
          this.authenmsg = "인증번호를 다시 확인해주세요.";

          if (data === true) {
            this.authenmsg = "인증되었습니다.";
            this.codeChk = true;
          } else {
            this.codeChk = false;
          }
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.EmailAuthenDialog = true;
        });
    },
    sendemail() {
      axios
        .post(`api/email`, {
          userEmail: this.user.email,
        })
        .then(({ data }) => {
          this.msg = "email 전송에 실패했습니다.";
          if (data === "전송성공") {
            this.msg = "인증번호가 전송되었습니다.";
            this.issendemail = true;
          } else if (data === "이메일중복") {
            this.msg = "이미 가입된 이메일입니다.";
          } else {
            this.msg = "이미 가입된 이메일입니다.";
          }
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.EmailDialog = true;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
        });
    },
    movePage() {
      this.$router.push({ name: "Login" });
    },
<<<<<<< HEAD

=======
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    goToMain() {
      this.$router.push({
        name: "MainView",
      });
    },
    sameChk(password) {
      if (this.user.userpwd == password) return true;
      else {
        this.valid = false;
        return false;
      }
    },
    register() {
      if (
        !this.user.email ||
        !this.user.usernickname ||
        !this.user.userpwd ||
<<<<<<< HEAD
        !this.user.avatar
      ) {
        this.isError = true;
        this.errorMsg = "이메일과 닉네임과 비밀번호를 모두 입력해주세요.";
=======
        this.user.avatar === null ||
        !this.codeChk ||
        !this.userIdChk ||
        !this.userNickChk ||
        !this.agreement
      ) {
        this.isError = true;
        this.errorMsg = "가입에 필요한 정보를 모두 입력해주세요.";
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
        return;
      }
      axios
        .post(`api/register/domain`, {
          userId: this.user.userid,
          userPassword: this.user.userpwd,
          userEmail: this.user.email,
          userNickname: this.user.usernickname,
          userAvatar: this.user.avatar,
        })
<<<<<<< HEAD
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            console.log(msg);
          }
          console.log(data);
          alert(msg);
=======
        .then(() => {
          this.registermsg = "등록이 완료되었습니다.";
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
          this.movePage();
        })
        .catch((error) => {
          console.log(error);
<<<<<<< HEAD
          alert("등록 실패입니다.");
        })
        .finally(() => {
          console.log(this.user.userid);
          console.log(this.user.userpwd);
          console.log(this.user.username);
=======
          this.registermsg = "등록 실패입니다.";
        })
        .finally(() => {
          if (this.registermsg === "등록이 완료되었습니다.") {
            this.movePage;
          } else {
            this.registerDialog = true;
          }
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
        });
    },
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
<<<<<<< HEAD
=======
      this.$refs.nicknameForm.reset();
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
<<<<<<< HEAD
  },
};
</script>
=======
    setavater(i) {
      this.user.avatar = i;
    },
  },
};
</script>

<style></style>
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
