package com.example.githubmvi.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface ViewEvent

interface ViewState

interface ViewSideEffect

const val SIDE_EFFECTS_KEY = "side-effects_key"

/**
 * Event: 사용자의 상호 작용을 나타내는 클래스 (예 : 사용자가 버튼을 클릭)
 * UiState : 화면의 상태를 나타내는 클래스 (예 : 로딩 중, 데이터 로드 완료, 오류 상태)
 * Effect : 화면의 상태 변경 외의 다른 작업을 나타내는 클래스 (예 : 네비게이션 이동, 토스트 메시지 표시, 다이얼로그 표시, 푸시 알림 표시 등)
 * */
abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect: ViewSideEffect> : ViewModel() {
        abstract fun setInitialState(): UiState
        abstract fun handleEvents(event: Event)

        private val initialState: UiState by lazy { setInitialState() }

        private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
        val viewState: State<UiState> = _viewState

        private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

        private val _effect: Channel<Effect> = Channel()
        val effect = _effect.receiveAsFlow()

        init {
            subscribeToEvents()
        }

        private fun subscribeToEvents() {
            viewModelScope.launch {
                _event.collect {
                    handleEvents(it)
                }
            }
        }

        fun setEvent(event: Event) {
            viewModelScope.launch { _event.emit(event) }
        }

        protected fun setState(reducer: UiState.() -> UiState) {
            val newState = viewState.value.reducer()
            _viewState.value = newState
        }

        protected fun setEffect(builder: () -> Effect) {
            val effectValue = builder()
            viewModelScope.launch { _effect.send(effectValue) }
        }
}