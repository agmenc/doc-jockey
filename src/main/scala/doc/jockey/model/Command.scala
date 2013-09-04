package doc.jockey.model

import doc.jockey.builder.Summary

trait Command {
  def execute: Summary
}

/**
 *
 * Three mappings:
 *  map(_.execute) ===> result.map(_.display) ===> HTML output
 *  map(_.code) ===> test spec
 *  map(_.display) ===> editing

  \
  |
  |- Cmd
  |
  |- Cmd
  |   |
  |   |- Cmd
  |   |
  |   |- Cmd
  |   |
  |   |- Cmd
  |
  |- Cmd
  |

  */