package org.scalatest

import doc.jockey.runners.scalatest.ScalaTestManipulation
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers._
import example.project.specs.sandbox.hole.Hider

class DocJockeySpecSpec extends WordSpec with ScalaTestManipulation with MockitoSugar {
  "Tests nested anywhere inside the sandbox folder are ignored" in {
    val spec = new Hider.SandboxedExampleSpec

    runSpec(spec)

    verify(spec.exposedEngine, never()).registerTest(anyString(), isA(classOf[() => Unit]))
    verify(spec.exposedEngine, times(1)).registerIgnoredTest(anyString(), isA(classOf[() => Unit]))
  }
}

trait MockJockeySpec extends DocJockeySpec with MockitoSugar {
  private[scalatest] override val engine = mock[EngineDriver]
  def exposedEngine = engine

  when(engine.testNames).thenReturn(Set("whatever"))
}