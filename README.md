# ParkEasy 🅿️

> 빠르고 편리한 주차장 예약 서비스

ParkEasy는 사용자의 현재 위치를 기반으로 주변 주차장을 검색하고 예약할 수 있는 Android 애플리케이션입니다.
JetpackCompose와 Clean Architecture 그리고 Multi-Module 학습을 위해 개발중인 프로젝트입니다. 

## 📋 목차

- [주요 기능](#주요-기능)
- [기술 스택](#기술-스택)
- [프로젝트 구조](#프로젝트-구조)
- [스크린샷](#스크린샷)
- [아키텍처](#아키텍처)

## 🚀 주요 기능

### 홈 화면
- 📍 실시간 위치 기반 지도 표시
- 🗺️ Google Maps를 활용한 인터랙티브 지도
- 🅿️ 주변 주차장 마커 표시 및 실시간 정보
- ⭐ 주변 주차장 찾기 및 즐겨찾기 기능

### 주변 주차장
- 📝 5km 반경 내 주차장 리스트 뷰
- 💰 시간당 요금 정보 제공
- 🕐 운영 시간 안내
- 🚗 실시간 주차 가능 공간 표시
- 📸 주차장 썸네일 이미지

### 마이페이지
- 👤 사용자 프로필 관리
- 🚙 차량 등록
- 💳 결제수단 등록
- 📜 예약 내역 조회
- ⚙️ 설정

## 🛠 기술 스택

### 핵심 프레임워크
- **Kotlin** 2.2.20
- **Jetpack Compose** - 최신 선언형 UI 프레임워크
- **Material Design 3** - 현대적인 UI/UX

### 아키텍처 및 패턴
- **Clean Architecture** - 계층 분리 및 의존성 관리
- **MVVM Pattern** - ViewModel을 활용한 상태 관리
- **Multi-Module** - 기능별 모듈화

### 주요 라이브러리

| 카테고리 | 라이브러리 | 버전 |
|---------|-----------|------|
| UI | Jetpack Compose | 2024.09.00 |
| 의존성 주입 | Hilt (Dagger) | 2.56.2 |
| 네비게이션 | Navigation Compose | 2.7.2 |
| 지도 | Google Maps Compose | 4.4.1 |
| 위치 | Play Services Location | 21.3.0 |
| 이미지 로딩 | Coil 3 | 3.3.0 |
| 백엔드/인증 | Firebase Auth | 34.3.0 |
| 비동기 처리 | Kotlin Coroutines | 1.7.3 |

### 개발 환경
- **최소 SDK**: 29 (Android 10)
- **타겟 SDK**: 36
- **컴파일 SDK**: 36
- **Java 버전**: 11

## 📂 프로젝트 구조

```
ParkEasy/
├── app/                          # 메인 애플리케이션 모듈
│   ├── navigation/               # 네비게이션 설정
│   ├── repository/               # 데이터 레이어 구현
│   └── ui/                       # 공통 UI 컴포넌트 및 테마
│
├── core/                         # 공통 핵심 모듈
│   ├── domain/                   # 도메인 레이어
│   │   ├── model/                # 도메인 모델
│   │   ├── repository/           # 레포지토리 인터페이스
│   │   └── usecase/              # 유스케이스
│   │
│   ├── data/                     # 데이터 레이어
│   │   ├── repository/           # 레포지토리 구현
│   │   └── di/                   # 의존성 주입 모듈
│   │
│   └── ui/                       # 재사용 가능한 UI 컴포넌트
│       └── components/           # 공통 컴포넌트
│
└── feature/                      # 기능별 독립 모듈
    ├── home/                     # 홈 화면 (지도 및 주변 주차장)
    ├── around/                   # 주변 주차장 리스트
    └── mypage/                   # 마이페이지
```

자세한 아키텍처 설명은 [아키텍처 문서](docs/ARCHITECTURE.md)를 참조하세요.

## 📱 스크린샷

_스크린샷은 추후 추가 예정_

## 🏗 아키텍처

ParkEasy는 **Clean Architecture**와 **MVVM 패턴**을 따릅니다.

### 계층 구조

```
┌─────────────────────────────────────┐
│     Presentation Layer (UI)         │
│  - Composables                      │
│  - ViewModels                       │
│  - UI State                         │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│     Domain Layer                    │
│  - Use Cases                        │
│  - Repository Interfaces            │
│  - Domain Models                    │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│     Data Layer                      │
│  - Repository Implementations       │
│  - Data Sources (Firebase, API)    │
│  - Data Models                      │
└─────────────────────────────────────┘
```

### 주요 설계 원칙

- **단일 책임 원칙**: 각 모듈과 클래스는 하나의 책임만 가짐
- **의존성 역전**: 상위 계층이 하위 계층에 의존하지 않음
- **관심사의 분리**: UI, 비즈니스 로직, 데이터 처리 분리
- **테스트 용이성**: 각 계층을 독립적으로 테스트 가능

자세한 내용은 [아키텍처 문서](docs/ARCHITECTURE.md)를 참조하세요.

## 📚 추가 문서

- [아키텍처 가이드](docs/ARCHITECTURE.md) - 상세한 아키텍처 설명
- [모듈 가이드](docs/MODULE_GUIDE.md) - 각 모듈의 역할과 사용법
