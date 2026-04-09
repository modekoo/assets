# 📊 Real-Time Market Data Pipeline (Upbit + Kafka + ELK)

실시간 코인 시세 데이터를 수집하고, Kafka를 통해 스트리밍한 뒤 Elasticsearch + Kibana로 시각화하는 프로젝트입니다.  
투자자산 코인, 주식 등에 사용할 기초 프로젝트입니다.
---

## 🧩 Architecture

```
Upbit API → Spring Boot → Kafka → Logstash → Elasticsearch → Kibana
```

* **Upbit API**: 실시간 마켓 데이터 수집
* **Spring Boot**: 데이터 수집 및 Kafka Producer
* **Kafka**: 이벤트 스트리밍 처리
* **Logstash**: Kafka → Elasticsearch 파이프라인
* **Elasticsearch**: 시계열 데이터 저장
* **Kibana**: 데이터 시각화

---

## ⚙️ Tech Stack

* Java 21
* Spring Boot 3.x
* Kafka (KRaft mode)
* Elasticsearch / Logstash / Kibana (ELK)
* Docker / Docker Compose

---

## 📦 Data Flow

1. Upbit REST API를 통해 마켓 데이터 조회
2. 데이터를 `MarketTickerDocument`로 변환
3. Kafka Topic (`market.ticker`)에 전송
4. Logstash에서 Kafka Consumer로 수신
5. Elasticsearch에 인덱싱 (`market-ticker-*`)
6. Kibana에서 실시간 시각화

---

## 🗂️ Index Strategy

* 월별 인덱스 사용

```
market-ticker-YYYY.MM
```

예:

```
market-ticker-2026.03
```

---

## 📄 Sample Document

```json
{
  "market": "KRW-BTC",
  "timestamp": 1774760926254,
  "tradePrice": 138.0,
  "openingPrice": 138.0,
  "highPrice": 139.0,
  "lowPrice": 137.0,
  "tradeVolume": 1438.84,
  "change": "RISE",
  "changeRate": 0.007
}
```

---

## 🚀 How to Run

### 1. Kafka 실행

```bash
cd infra/kafka
docker compose up -d
```

---

### 2. ELK 실행

```bash
cd infra/elk
docker compose up -d
```

---

### 3. Spring Boot 실행

```bash
./gradlew bootRun
```

---

## 🔐 Environment Variables

```bash
UPBIT_ACCESS_KEY=your_key
UPBIT_SECRET_KEY=your_key
```

---

## 📈 Kibana Visualization

* Index Pattern: `market-ticker-*`
* Time Field: `@timestamp`
* Visualization:

    * X-axis: `@timestamp`
    * Y-axis: `tradePrice`
    * Filter: `market = KRW-BTC`

---

## 💡 Features

* 실시간 코인 시세 스트리밍
* Kafka 기반 이벤트 처리
* ELK 기반 시계열 데이터 분석
* Kibana를 통한 시각화

---

## 🧠 Future Work

* 이동평균선 (MA) 추가
* 변동성 기반 알림 시스템
* 자동매매 전략 연동
* Kafka Streams 기반 실시간 분석

---

## 📌 Notes

* Kibana visualization은 데이터 집계 방식에 따라 다르게 보일 수 있습니다.
* 시세 변동이 적은 경우 그래프가 평평하게 보일 수 있습니다.(ex doge)

---

## 🙋‍♂️ Author

Backend Engineer  
modekoo
